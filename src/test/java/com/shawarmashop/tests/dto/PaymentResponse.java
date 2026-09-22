package com.shawarmashop.tests.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private Integer id;
    private Integer orderId;
    private Integer amount;
    private String method;
    private String status;
    private String failureReason;
    private Instant createdAt;
}
