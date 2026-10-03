package com.shawarmashop.tests.kafka;

import com.shawarmashop.tests.kafka.events.StockEvent;

import java.time.Duration;
import java.util.function.Predicate;

public class StockEventsTopic extends KafkaTopic<StockEvent> {

    private static final String TOPIC = "order.events";
    private static final Duration DEFAULT_DURATION = Duration.ofSeconds(5);

    protected StockEventsTopic(String groupId) {
        super(TOPIC, StockEvent.class, groupId);
    }

    public StockEvent awaitRestockPlaced(long ingredientId, Duration duration) {
        return awaitMessage("RESTOCK_PLACED", byIngredientId(ingredientId), duration);
    }

    public StockEvent awaitRestockPlaced(long ingredientId) {
        return awaitRestockPlaced(ingredientId, DEFAULT_DURATION);
    }

    public StockEvent awaitRestockFailed(long ingredientId, Duration duration) {
        return awaitMessage("RESTOCK_FAILED", byIngredientId(ingredientId), duration);
    }

    public StockEvent awaitRestockFailed(long ingredientId) {
        return awaitRestockFailed(ingredientId, DEFAULT_DURATION);
    }

    public StockEvent awaitStockLow(long ingredientId, Duration duration) {
        return awaitMessage("STOCK_LOW", byIngredientId(ingredientId), duration);
    }

    public StockEvent awaitStockLow(long ingredientId) {
        return awaitStockLow(ingredientId, DEFAULT_DURATION);
    }

    private Predicate<StockEvent> byIngredientId(long ingredientId) {
        return stockEvent -> stockEvent.getIngredientId() != null && stockEvent.getIngredientId() == ingredientId;
    }
}
