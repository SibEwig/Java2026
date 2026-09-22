package com.shawarmashop.tests.rest.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.shawarmashop.tests.auth.ApiClient;
import com.shawarmashop.tests.dto.IngredientsResponse;
import com.shawarmashop.tests.dto.RestockResponse;
import com.shawarmashop.tests.rest.ApiResult;
import com.shawarmashop.tests.rest.BaseClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class IngredientClient extends BaseClient {

    private static final String INGREDIENTS_PATH = "/ingredients";
    private static final String RESTOCK_PATH = "/ingredients/{id}/restock";
    private static final String LOW_STOCK_QUERY = "lowStock";

    public IngredientClient(ApiClient apiClient) {
        super(apiClient);
    }

    public ApiResult<List<IngredientsResponse>> ingredients() {
        return ingredients(null);
    }

    @Step("GET /ingredients (lowsStock={0})")
    public ApiResult<List<IngredientsResponse>> ingredients(Boolean lowStock) {
        RequestSpecification request = given(spec());
        if (lowStock != null) request.queryParam(LOW_STOCK_QUERY, lowStock);
        Response response = request.get(INGREDIENTS_PATH);
        return ApiResult.from(response, new TypeReference<>() {});
    }

    @Step("Post /ingredients/{0}/restock (qty={1})")
    public ApiResult<RestockResponse> restock(int id, int qty) {
        Response response = given(spec())
                .pathParam("id", id)
                .body(Map.of("qty", qty))
                .post(RESTOCK_PATH);
        return ApiResult.from(response, RestockResponse.class);
    }
}
