package com.oop;

interface Discountable {

    int discountPercent();

    default int applyDiscount(int amount) {
        return amount - amount * discountPercent() / 100;
    }
}
