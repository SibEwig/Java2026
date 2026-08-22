package com.base.restassured;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloRestAssuredTest {

    private static final String BASE_URL = "https://shawarma.threadqa.ru";

    private final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(BASE_URL)
            .setBasePath("/api/v1")
            .addFilter(new AllureRestAssured())
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
        assertThat(loginResponse.getType())
                .isEqualTo("Bearer");
    }
}
