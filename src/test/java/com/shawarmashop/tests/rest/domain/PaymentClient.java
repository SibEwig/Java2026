package com.shawarmashop.tests.rest.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.shawarmashop.tests.auth.ApiClient;
import com.shawarmashop.tests.dto.PayRequest;
import com.shawarmashop.tests.dto.PaymentResponse;
import com.shawarmashop.tests.dto.PaymentWebhookRequest;
import com.shawarmashop.tests.rest.ApiResult;
import com.shawarmashop.tests.rest.BaseClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PaymentClient extends BaseClient {

    private static final String PAY_PATH = "/orders/{id}/pay";
    private static final String PAYMENTS_PATH = "/orders/{id}/payments";
    private static final String WEBHOOKS_PATH = "/webhooks/payments";
    private static final String IDEMPOTENCY_HEADER = "Idempotency-Key";

    public PaymentClient(ApiClient apiClient) {
        super(apiClient);
    }

    @Step("POST /orders/{0}/pay")
    public ApiResult<PaymentResponse> pay(long orderId, String key, PayRequest request) {
        Response response = given(spec())
                .header(IDEMPOTENCY_HEADER, key)
                .body(request)
                .pathParam("id", orderId)
                .post(PAY_PATH);
        return ApiResult.from(response, PaymentResponse.class);
    }

    @Step("GET /orders/{0}/payments")
    public ApiResult<List<PaymentResponse>> list(long orderId) {
        Response response = given(spec())
                .pathParam("id", orderId)
                .get(PAYMENTS_PATH);
        return ApiResult.from(response, new TypeReference<>() {
        });
    }

    @Step("POST /webhooks/payments")
    public ApiResult<Void> sendWebhook(PaymentWebhookRequest request) {
        Response response = given(spec())
                .body(request)
                .post(WEBHOOKS_PATH);
        return ApiResult.from(response, void.class);
    }
}
