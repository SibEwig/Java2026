package com.shawarmashop.tests.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class PaymentWebhookRequest {
    private String txnId;
    private String status;
    private Instant occurredAt;
    private String signature;
}
