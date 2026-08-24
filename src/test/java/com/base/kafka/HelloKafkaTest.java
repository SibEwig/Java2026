package com.base.kafka;

import com.base.restassured.LoginRequest;
import com.base.restassured.LoginResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import net.datafaker.idnumbers.SouthAfricanIdNumber;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HelloKafkaTest {

    private final static String KAFKA_SERVER = "shawarma.threadqa.ru:9094";
    private final static String TOPIC = "order.events";

    private static final String BASE_URL = "https://shawarma.threadqa.ru";

    private final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(BASE_URL)
            .setBasePath("/api/v1")
            .addFilter(new AllureRestAssured())
            .build();

    @Test
    @SneakyThrows
    public void test1() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-" + UUID.randomUUID());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        LoginRequest loginRequest = new LoginRequest("owner", "owner123");
        LoginResponse loginResponse = given(requestSpecification)
                .body(loginRequest)
                .when()
                .post("/auth/login")
                .then()
                .log().all()
                .extract()
                .as(LoginResponse.class);

        int orderId = given(requestSpecification)
                .when()
                .auth().oauth2(loginResponse.getToken())
                .body("{\"recipeId\":4,\"qty\":1,\"payment\":{\"method\":\"CARD\"}}")
                .when()
                .post("/orders")
                .then()
                .log().all()
                .statusCode(201)
                .extract().jsonPath().getInt("id");

        try(KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(List.of(TOPIC));

            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(20));

            ObjectMapper objectMapper = new ObjectMapper();

            for (ConsumerRecord<String, String> record : poll) {
                JsonNode jsonNode = objectMapper.readTree(record.value());
                if (!jsonNode.path("type").asText().equals("ORDER_PLACED")) {
                    continue;
                }
                JsonNode payload = jsonNode.path("payload");
                OrderPlacedEvent orderPlacedEvent = objectMapper.convertValue(payload, OrderPlacedEvent.class);

                assertThat(orderPlacedEvent.getOrderId()).isEqualTo(orderId);
            }
        }
    }
}
