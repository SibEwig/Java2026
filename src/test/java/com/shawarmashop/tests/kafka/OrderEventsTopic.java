package com.shawarmashop.tests.kafka;

import com.shawarmashop.tests.kafka.events.OrderEvent;

import java.time.Duration;
import java.util.function.Predicate;

public final class OrderEventsTopic extends KafkaTopic<OrderEvent> {

    public static final String TOPIC = "order.events";
    public static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(15);

    public OrderEventsTopic(String groupId) {
        super(TOPIC, OrderEvent.class, groupId);
    }

    public OrderEvent awaitPlaced(long orderId) {
        return awaitMessage("ORDER_PLACED", byOrderId(orderId), DEFAULT_TIMEOUT);
    }

    public OrderEvent awaitPlaced(long orderId, Duration duration) {
        return awaitMessage("ORDER_PLACED", byOrderId(orderId), duration);
    }

    public OrderEvent awaitPaid(long orderId) {
        return awaitMessage("ORDER_PAID", byOrderId(orderId), DEFAULT_TIMEOUT);
    }

    public OrderEvent awaitPaid(long orderId, Duration duration) {
        return awaitMessage("ORDER_PAID", byOrderId(orderId), duration);
    }

    public OrderEvent awaitCancelled(long orderId) {
        return awaitMessage("ORDER_CANCELLED", byOrderId(orderId), DEFAULT_TIMEOUT);
    }

    public OrderEvent awaitCancelled(long orderId, Duration duration) {
        return awaitMessage("ORDER_CANCELLED", byOrderId(orderId), duration);
    }

    public OrderEvent awaitDone(long orderId) {
        return awaitMessage("ORDER_DONE", byOrderId(orderId), DEFAULT_TIMEOUT);
    }

    public OrderEvent awaitDone(long orderId, Duration duration) {
        return awaitMessage("ORDER_DONE", byOrderId(orderId), duration);
    }

    public OrderEvent awaitBreadReservationFailed(long orderId) {
        return awaitMessage("BREAD_RESERVATION_FAILED", byOrderId(orderId), DEFAULT_TIMEOUT);
    }

    public OrderEvent awaitBreadReservationFailed(long orderId, Duration duration) {
        return awaitMessage("BREAD_RESERVATION_FAILED", byOrderId(orderId), duration);
    }

    private static Predicate<OrderEvent> byOrderId(long orderId) {
        return event -> event.getOrderId() != null && event.getOrderId() == orderId;
    }
}
