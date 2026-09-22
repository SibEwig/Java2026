package com.shawarmashop.tests.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponse {
    private Long id;
    private String name;
    private String size;
    private Long prepSeconds;
    private List<RecipeIngredientView> ingredients;
    private String imageIrl;
    private RecipeRating rating;
}
