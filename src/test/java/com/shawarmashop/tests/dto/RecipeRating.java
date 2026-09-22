package com.shawarmashop.tests.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRating {
    private Long recipeId;
    private Integer averageStars;
    private Integer totalReviews;
    private String topComplaint;
    private String trend;
}
