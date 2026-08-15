package com.base.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonTest {

    private final
    ObjectMapper mapper = new ObjectMapper();

    @Test
    @SneakyThrows
    public void test1() {
        String json = """
                {"id":42,"status":"PAID","price":49.90}
                """;

        OrderResponse orderResponse = mapper.readValue(json, OrderResponse.class);
    }

    @Test
    @SneakyThrows
    public void test2() {
        String json = """
                [
                    {"id":42,"status":"PAID","price":49.90},
                    {"id":43,"status":"CREATED","price":15.90}
                ]
                """;

        List<OrderResponse> orderResponses = mapper.readValue(json, new TypeReference<>() {
        });
        assertThat(orderResponses).hasSize(2);
    }

    @Test
    @SneakyThrows
    public void test3() {
        OrderRequest orderRequest = OrderRequest.builder()
                .quantity(1)
                .recipeId(10)
                .build();

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderRequest);
        System.out.println(json);
    }
}
