package com.base.kafka;

import lombok.Data;

@Data
public class OrderPlacedEvent {
    private Integer orderId;
    private Integer recipeId;
    private String recipeName;
    private Integer qty;
    private Double totalPrice;
}
