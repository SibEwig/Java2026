package com.lambdas;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

class Main {

    public static void main(String[] args) {

        List<OrderRow> orders = List.of(
                new OrderRow(
                        1,
                        "Alice",
                        OrderStatus.PAID,
                        PaymentChoice.CARD,
                        320
                ),
                new OrderRow(
                        2,
                        "Bob",
                        OrderStatus.CREATED,
                        PaymentChoice.CASH,
                        450
                ),
                new OrderRow(
                        3,
                        "Alice",
                        OrderStatus.COMPLETED,
                        PaymentChoice.CARD,
                        780
                ),
                new OrderRow(
                        4,
                        "Charlie",
                        OrderStatus.PREPARING,
                        PaymentChoice.CRYPTO,
                        560
                ),
                new OrderRow(
                        5,
                        "Bob",
                        OrderStatus.PAID,
                        PaymentChoice.CARD,
                        290
                ),
                new OrderRow(
                        6,
                        "Alice",
                        OrderStatus.PAID,
                        PaymentChoice.CASH,
                        640
                ),
                new OrderRow(
                        7,
                        "Diana",
                        OrderStatus.CANCELLED,
                        PaymentChoice.CARD,
                        410
                ),
                new OrderRow(
                        8,
                        "Charlie",
                        OrderStatus.READY,
                        PaymentChoice.CASH,
                        850
                ),
                new OrderRow(
                        9,
                        "Alice",
                        OrderStatus.PAID,
                        PaymentChoice.CRYPTO,
                        370
                ),
                new OrderRow(
                        10,
                        "Diana",
                        OrderStatus.COMPLETED,
                        PaymentChoice.CARD,
                        920
                )
        );

        OrderAnalytics
                .paidByCustomer(orders, "Alice")
                .forEach(System.out::println);

        System.out.println(
                OrderAnalytics.revenue(orders)
        );

        System.out.println(
                OrderAnalytics.countByStatus(orders)
        );

        System.out.println(
                OrderAnalytics.revenueByPayment(orders)
        );

        System.out.println(
                OrderAnalytics.page(orders, 2, 4)
        );
    }
}
