package com.shawarmashop.tests.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class KafkaTestBus implements AutoCloseable {

    private final String groupId = "tests-" + UUID.randomUUID();

    private OrderEventsTopic orders;
    private StockEventsTopic stocks;

    public OrderEventsTopic orders() {
        if (orders == null) orders = new OrderEventsTopic(groupId);
        return orders;
    }

    public StockEventsTopic stocks() {
        if (stocks == null) stocks = new StockEventsTopic(groupId);
        return stocks;
    }

    @Override
    public void close() throws Exception {
        for (KafkaTopic<?> topic : inits()) {
            try {
                topic.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private List<KafkaTopic<?>> inits() {
        List<KafkaTopic<?>> list = new ArrayList<>();
        if (orders != null) list.add(orders);
        if (stocks != null) list.add(stocks);
        return list;
    }
}
