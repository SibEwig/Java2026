package com.shawarmashop.tests.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientInfo {
    private Long ingredientId;
    private String name;
    private Integer qtyNeeded;
    private String unit;
    private Integer onHand;
}
