package com.oop;

class Drink extends MenuItem {

    public Drink(String name, int basePrice) {
        super(name, basePrice);
    }

    @Override
    int price() {
        return basePrice;
    }

    @Override
    String kind() {
        return "Напиток";
    }
}
