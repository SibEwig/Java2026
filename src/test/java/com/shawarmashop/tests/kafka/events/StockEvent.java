package com.shawarmashop.tests.kafka.events;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockEvent {
    private Long ingredientId;
    private String ingredientName;
    private String invoiceNumber;
    private String status;
    private String reason;
    private Integer onHand;
    private Integer minLevel;
}
