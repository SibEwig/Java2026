package com.oop;

class Combo extends MenuItem implements Discountable {

    public Combo(String name, int basePrice) {
        super(name, basePrice);
    }

    @Override
    int price() {
        return basePrice;
    }

    @Override
    String kind() {
        return "Комбо";
    }

    @Override
    public int discountPercent() {
        return 15;
    }
}
