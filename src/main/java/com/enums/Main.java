package com.enums;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Order> cases = List.of(
                new Order(0, RecipeSize.M, 2, PaymentChoice.CARD),
                new Order(42, null, 2, PaymentChoice.CARD),
                new Order(42, RecipeSize.L, 15, PaymentChoice.CARD),
                new Order(42, RecipeSize.L, 2, null),
                new Order(42, RecipeSize.L, 5, PaymentChoice.CASH),
                new Order(42, RecipeSize.L, 2, PaymentChoice.CASH)
        );

        for (Order order : cases) {
            runCase(order);
        }
    }

    private static void runCase(Order order) {
        try {
            int price = OrderValidator.validateAndPrice(order);
            System.out.println("[OK] price=" + price);
        } catch (OrderValidationException e) {
            System.out.println("[REJECT] " + e.getMessage());
        }
    }
}
