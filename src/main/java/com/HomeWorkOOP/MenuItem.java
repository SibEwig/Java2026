package com.HomeWorkOOP;

abstract class MenuItem {

    protected final String name;
    protected final int basePrice;

    public MenuItem(String name, int basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    void printReceipt() {
        System.out.println("[" + kind() + "] " + name + " — " + price() + " руб");
    }

    abstract int price();

    abstract String kind();
}