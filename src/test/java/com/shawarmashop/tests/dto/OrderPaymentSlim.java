package com.shawarmashop.tests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentSlim {

    @JsonProperty("method")
    private String method;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("txnId")
    private String txnId;

    @JsonProperty("status")
    private String status;

}