package com.shawarmashop.tests.db.repository;

import com.shawarmashop.tests.db.Jdbc;
import com.shawarmashop.tests.db.RowMapper;
import com.shawarmashop.tests.db.TestDatabase;
import com.shawarmashop.tests.db.rows.OrderRow;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static org.awaitility.Awaitility.await;

public class OrderRepository {
    private final Jdbc jdbc = TestDatabase.jdbc();

    private static final RowMapper<OrderRow> MAPPER = resultSet ->
            OrderRow.builder()
                    .status(resultSet.getString("status"))
                    .qty(resultSet.getInt("qty"))
                    .etaAt(resultSet.getTimestamp("eta_at").toInstant())
                    .id(resultSet.getLong("id"))
                    .breadBatchId(resultSet.getString("bread_batch_id"))
                    .completedAt(resultSet.getTimestamp("completed_at").toInstant())
                    .paymentMethod(resultSet.getString("payment_method"))
                    .recipeId(resultSet.getLong("recipe_id"))
                    .placedAt(resultSet.getTimestamp("placed_at").toInstant())
                    .totalPrice(resultSet.getInt("total_price"))
                    .build();

    private static final String SELECT_ALL = """
            SELECT status, qty, eta_at, id, bread_batch_id,
                   completed_at, payment_method, recipe_id,
                   placed_at, total_price
            FROM orders
            """;

    public Optional<OrderRow> findById(long id) {
        String sql = SELECT_ALL + "WHERE id = ?";
        return jdbc.queryForObject(sql, MAPPER, id);
    }

    public List<OrderRow> findByStatus(String status) {
        String sql = SELECT_ALL + "WHERE status = ? ORDER BY id";
        return jdbc.queryForList(sql, MAPPER, status);
    }

    public OrderRow awaitStatus(long orderId, String expected, Duration duration) {
        await("Заказ " + orderId + " -> " + expected)
                .atMost(duration)
                .pollInterval(Duration.ofMillis(250))
                .until(() ->
                    findById(orderId)
                            .map(orderRow -> orderRow.getStatus().equals(expected))
                            .orElseThrow(() ->
                                    new AssertionError("Заказ " + orderId + " отсутствует в БД")));
        return findById(orderId).orElseThrow();
    }
}
