package com.base.lombok;

public class DataExampleTest {
    public static void main(String[] args) {
        Order order = new Order(1, "PAID", 10.99);
        Order order2 = Order.builder()
                .status("CREATED")
                .id(2)
                .price(15.50)
                .build();
        System.out.println(order2);
    }
}
