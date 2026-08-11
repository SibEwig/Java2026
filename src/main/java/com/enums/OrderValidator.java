package com.enums;

public class OrderValidator {

    static int validateAndPrice(Order order) {
        if (order.recipeId() <= 0) throw new OrderValidationException("recipeId must be positive");
        if (order.size() == null) throw new OrderValidationException("size is required");
        if (order.qty() < 1 || order.qty() > 10) throw new OrderValidationException("qty must be between 1 and 10");
        if (order.payment() == null) throw new OrderValidationException("payment is required");

        int total = order.size().getBasePrice() * order.qty();
        if (total > 1000 && !order.payment().isOnline())
            throw new OrderValidationException("cash not allowed for orders over 1000");
        return total;
    }
}
