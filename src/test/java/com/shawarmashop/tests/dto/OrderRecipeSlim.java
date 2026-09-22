package com.shawarmashop.tests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRecipeSlim {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("size")
    private String size;

    @JsonProperty("price")
    private Integer price;

}