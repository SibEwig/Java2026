package com.lambdas;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderAnalytics {

    static List<OrderRow> paidByCustomer(
            List<OrderRow> orders,
            String customer
    ) {
        return orders.stream()
                .filter(x -> x.customer().equals(customer) && x.status() == OrderStatus.PAID)
                .toList();
    }

    static double revenue(
            List<OrderRow> orders
    ) {
        return orders.stream()
                .filter(x -> x.status() != OrderStatus.CANCELLED)
                .mapToDouble(OrderRow::total)
                .sum();
    }

    static Map<OrderStatus, Long> countByStatus(
            List<OrderRow> orders
    ) {
        return orders.stream()
                .collect(Collectors.groupingBy(OrderRow::status, Collectors.counting()));
    }

    static Map<PaymentChoice, Double> revenueByPayment(
            List<OrderRow> orders
    ) {
        return orders.stream()
                .filter(x -> x.status() != OrderStatus.CANCELLED)
                .collect(Collectors.groupingBy(OrderRow::payment, Collectors.summingDouble(OrderRow::total))
                );
    }

    static Page<OrderRow> page(
            List<OrderRow> orders,
            int pageNum,
            int pageSize
    ) {
        long elementsToSkip = (long) (pageNum - 1) * pageSize;
        List<OrderRow> result = orders.stream()
                .skip(elementsToSkip)
                .limit(pageSize)
                .toList();
        return new Page<>(
                result,
                pageNum,
                pageSize,
                orders.size()
        );
    }
}
