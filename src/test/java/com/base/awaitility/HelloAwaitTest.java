package com.base.awaitility;

import com.base.restassured.LoginRequest;
import com.base.restassured.LoginResponse;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;

public class HelloAwaitTest {

    private static final String BASE_URL = "https://shawarma.threadqa.ru";

    private final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(BASE_URL)
            .setBasePath("/api/v1")
            .addFilters(List.of(new AllureRestAssured(), new ResponseLoggingFilter()))
            .build();

    @Test
    public void test1() {
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
                .extract().jsonPath().getInt("id");

        given(requestSpecification)
                .auth().oauth2(loginResponse.getToken())
                .pathParam("id", orderId)
                .body("{\"method\":\"CARD\"}")
                .header("Idempotency-Key", UUID.randomUUID())
                .post("/orders/{id}/pay")
                .then()
                .statusCode(202);

        await("Ожидание готовности заказа " + orderId)
                .atMost(Duration.ofSeconds(60))
                .pollInterval(Duration.ofSeconds(5))
                .untilAsserted(() -> {
                    given(requestSpecification)
                            .auth().oauth2(loginResponse.getToken())
                            .pathParam("id", orderId)
                            .get("/orders/{id}/preparation")
                            .then()
                            .body("status", equalTo("DONE"));
                });

    }
}
