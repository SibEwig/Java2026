package com.shawarmashop.tests.db.rows;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class PaymentRow {
    private Long id;
    private BigDecimal amount;
    private Instant createdAt;
    private String idempotencyKey;
    private String method;
    private String status;
    private String txnId;
    private Integer orderId;
}
