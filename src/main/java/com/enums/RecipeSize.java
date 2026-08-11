package com.enums;

public enum RecipeSize {
    S(149),
    M(199),
    L(269);

    private final int basePrice;

    RecipeSize(int price) {
        this.basePrice = price;
    }

    public int getBasePrice() {
        return basePrice;
    }
}
