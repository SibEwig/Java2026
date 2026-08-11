package com.enums;

public record Order(long recipeId, RecipeSize size, int qty, PaymentChoice payment) {}
