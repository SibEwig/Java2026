package com.shawarmashop.tests.kafka.events;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.Instant;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderEvent {
    private Long orderId;
    private Long recipeId;
    private String recipeName;
    private Integer qty;
    private Integer totalPrice;
    private String txnId;
    private Instant etaAt;
    private Instant completedAt;
    private String reason;
}
