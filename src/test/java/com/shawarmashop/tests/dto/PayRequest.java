package com.shawarmashop.tests.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayRequest {
    private String method;
}
