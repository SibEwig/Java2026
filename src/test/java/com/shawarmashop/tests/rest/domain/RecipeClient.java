package com.shawarmashop.tests.rest.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.shawarmashop.tests.auth.ApiClient;
import com.shawarmashop.tests.dto.*;
import com.shawarmashop.tests.rest.ApiResult;
import com.shawarmashop.tests.rest.BaseClient;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RecipeClient extends BaseClient {

    private static final String RECIPES_PATH = "/recipes";
    private static final String RECIPE_BY_ID_PATH = RECIPES_PATH + "/{id}";
    private static final String RECIPE_IMAGE_PATH = RECIPE_BY_ID_PATH + "/image";
    private static final String RECIPE_INGREDIENTS_PATH = RECIPE_BY_ID_PATH + "/ingredients";
    private static final String RECIPE_REVIEWS_PATH = RECIPE_BY_ID_PATH + "/reviews";

    public RecipeClient(ApiClient apiClient) {
        super(apiClient);
    }

    public ApiResult<Page<RecipeResponse>> list() {
        return list(Map.of());
    }

    @Step("GET /recipes")
    public ApiResult<Page<RecipeResponse>> list(Map<String, Object> queryParams) {
        Response response = given(spec())
                .queryParams(queryParams)
                .get(RECIPES_PATH);
        return ApiResult.from(response, new TypeReference<>() {
        });
    }

    @Step("GET /recipes/{0}")
    public ApiResult<RecipeResponse> get(long id) {
        Response response = given(spec())
                .pathParam("id", id)
                .get(RECIPE_BY_ID_PATH);
        return ApiResult.from(response, RecipeResponse.class);
    }

    @Step("POST /recipes/{0}/image")
    public ApiResult<Void> uploadImage(long id, String fileName, String contentType, byte[] bytes) {
        Response response = given(spec())
                .contentType(ContentType.MULTIPART)
                .pathParam("id", id)
                .multiPart("file", fileName, bytes, contentType)
                .post(RECIPE_IMAGE_PATH);
        return ApiResult.from(response, Void.class);
    }

    @Step("GET /recipes/{id}/ingredients")
    public ApiResult<RecipeIngredientResponse> ingredients(long id) {
        Response response = given(spec())
                .pathParam("id", id)
                .get(RECIPE_INGREDIENTS_PATH);
        return ApiResult.from(response, RecipeIngredientResponse.class);
    }

    @Step("GET /recipes/{0}/reviews (page={1})")
    public ApiResult<ReviewPage> reviews(long id, int page) {
        Response response = given(spec())
                .pathParam("id", id)
                .queryParam("page", page)
                .get(RECIPE_REVIEWS_PATH);
        return ApiResult.from(response, ReviewPage.class);
    }

    public ApiResult<ReviewPage> reviews(long id) {
        return reviews(id, 0);
    }

    @Step("PATCH /api/v1/recipes/{0}")
    public ApiResult<RecipeResponse> update(long id, UpdateRecipeRequest request) {
        Response response = given(spec())
                .pathParam("id", id)
                .body(request)
                .patch(RECIPE_BY_ID_PATH);
        return ApiResult.from(response, RecipeResponse.class);
    }
}
