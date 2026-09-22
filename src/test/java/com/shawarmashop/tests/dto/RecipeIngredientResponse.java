package com.shawarmashop.tests.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientResponse {
    private String recipeName;
    private List<IngredientInfo> ingredients;
}
