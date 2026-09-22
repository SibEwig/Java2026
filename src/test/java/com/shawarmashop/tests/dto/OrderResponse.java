package com.shawarmashop.tests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("recipe")
    private OrderRecipeSlim recipe;

    @JsonProperty("qty")
    private Integer qty;

    @JsonProperty("totalPrice")
    private Integer totalPrice;

    @JsonProperty("placedAt")
    private Instant placedAt;

    @JsonProperty("etaAt")
    private Instant etaAt;

    @JsonProperty("payment")
    private OrderPaymentSlim payment;

}