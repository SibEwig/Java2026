package com.shawarmashop.tests.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientsResponse {
    private Integer id;
    private String name;
    private String unit;
    private Integer onHand;
    private Integer minLevel;
    private SupplierInfo supplier;
}
