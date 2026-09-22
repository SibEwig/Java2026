package com.shawarmashop.tests.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateOrderRequest {
    private Integer recipeId;
    private Integer qty;
    private PaymentChoice payment;

    public static CreateOrderRequest of(int recipeId, int qty, String method) {
        return CreateOrderRequest.builder()
                .recipeId(recipeId)
                .qty(qty)
                .payment(new PaymentChoice(method))
                .build();
    }
}
