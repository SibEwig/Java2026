package com.shawarmashop.tests.rest.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.shawarmashop.tests.auth.ApiClient;
import com.shawarmashop.tests.dto.*;
import com.shawarmashop.tests.rest.ApiResult;
import com.shawarmashop.tests.rest.BaseClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderClient extends BaseClient {

    private static final String ORDERS_PATH = "/orders";
    private static final String ORDER_BY_ID_PATH = ORDERS_PATH + "/{id}";
    private static final String CANCEL_ORDER_PATH = ORDER_BY_ID_PATH + "/cancel";
    private static final String PREPARATION_PATH = ORDER_BY_ID_PATH + "/preparation";

    public OrderClient(ApiClient apiClient) {
        super(apiClient);
    }

    @Step("POST /orders")
    public ApiResult<OrderResponse> create(CreateOrderRequest request) {
        Response response = given(spec())
                .body(request)
                .post(ORDERS_PATH);
        return ApiResult.from(response, OrderResponse.class);
    }

    @Step("GET /orders (filter={0})")
    public ApiResult<Page<OrderResponse>> list(OrderFilter orderFilter) {
        Response response = given(spec())
                .queryParams(orderFilter.toQueryParams())
                .get(ORDERS_PATH);
        return ApiResult.from(response, new TypeReference<>() {
        });

    }

    @Step("GET /orders/{0}")
    public ApiResult<PreparationResponse> preparation(int id) {
        Response response = given(spec())
                .pathParam("id", id)
                .get(PREPARATION_PATH);
        return ApiResult.from(response, PreparationResponse.class);
    }

    @Step("GET /orders/{0}")
    public ApiResult<OrderResponse> get(int id) {
        Response response = given(spec())
                .pathParam("id", id)
                .get(ORDER_BY_ID_PATH);
        return ApiResult.from(response, OrderResponse.class);
    }

    @Step("POST /orders/{0}/cancel")
    public ApiResult<OrderResponse> cancel(int id) {
        Response response = given(spec())
                .pathParam("id", id)
                .post(CANCEL_ORDER_PATH);
        return ApiResult.from(response, OrderResponse.class);
    }
}
