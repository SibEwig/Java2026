package com.shawarmashop.tests.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UpdateRecipeRequest {
    private String name;
    private String description;
    private BigDecimal price;
}
