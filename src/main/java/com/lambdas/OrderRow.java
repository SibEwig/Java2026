package com.lambdas;

record OrderRow(
        long id,
        String customer,
        OrderStatus status,
        PaymentChoice payment,
        double total) {
}
