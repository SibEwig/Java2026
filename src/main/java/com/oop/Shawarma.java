package com.oop;

class Shawarma extends MenuItem {

    private final boolean xl;

    public Shawarma(String name, int basePrice, boolean xl) {
        super(name, basePrice);
        this.xl = xl;
    }

    @Override
    public String kind() {
        return "Шаурма";
    }

    @Override
    public int price() {
        return xl ? basePrice : basePrice + 100;
    }
}
