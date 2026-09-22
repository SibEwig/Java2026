package com.shawarmashop.tests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewPage {
    private Long recipeId;
    private Integer totalCount;
    private Double averageStars;
    private Integer page;
    @JsonProperty("items")
    private List<Review> reviews;
}
