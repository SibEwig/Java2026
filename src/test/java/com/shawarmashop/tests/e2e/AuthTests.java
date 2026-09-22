package com.shawarmashop.tests.e2e;

import com.shawarmashop.tests.auth.ApiClient;
import com.shawarmashop.tests.auth.Users;
import com.shawarmashop.tests.dto.CreateOrderRequest;
import com.shawarmashop.tests.dto.OrderFilter;
import com.shawarmashop.tests.dto.OrderResponse;
import com.shawarmashop.tests.dto.Page;
import org.junit.jupiter.api.Test;

public class AuthTests {

    @Test
    public void test1() {
        ApiClient apiClient = ApiClient.asUser(Users.OWNER);
        OrderResponse card = apiClient
                .orders()
                .create(CreateOrderRequest.of(1, 2, "CARD"))
                .success();
        Page<OrderResponse> success = apiClient
                .orders()
                .list(OrderFilter.builder()
                        .recipeId(1)
                        .build())
                .success();

    }
}
