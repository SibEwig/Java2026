package com.shawarmashop.tests.db.repository;

import com.shawarmashop.tests.db.Jdbc;
import com.shawarmashop.tests.db.RowMapper;
import com.shawarmashop.tests.db.TestDatabase;
import com.shawarmashop.tests.db.rows.PaymentRow;

import java.util.List;
import java.util.Optional;

public class PaymentRepository {

    private final Jdbc jdbc = TestDatabase.jdbc();
    private static final RowMapper<PaymentRow> MAPPER = resultSet -> PaymentRow.builder()
            .id(resultSet.getLong("id"))
            .amount(resultSet.getBigDecimal("amount"))
            .txnId(resultSet.getString("txn_id"))
            .status(resultSet.getString("status"))
            .createdAt(resultSet.getTimestamp("created_at").toInstant())
            .idempotencyKey(resultSet.getString("idempotency_key"))
            .method(resultSet.getString("method"))
            .orderId(resultSet.getInt("order_id"))
            .build();
    private static final String SELECT_ALL = """
            SELECT id, amount, txn_id, status, created_at,
            idempotency_key, method, order_id
            FROM payments
            """;

    public Optional<PaymentRow> findById(long id) {
        String sql = SELECT_ALL + "WHERE id = ?";
        return jdbc.queryForObject(sql, MAPPER, id);
    }

    public Optional<PaymentRow> findByIdempotencyKey(String idempotencyKey) {
        String sql = SELECT_ALL + "WHERE idempotency_key = ?";
        return jdbc.queryForObject(sql, MAPPER, idempotencyKey);
    }

    public Optional<PaymentRow> findByTxnId(String txnId) {
        String sql = SELECT_ALL + "WHERE txn_id = ?";
        return jdbc.queryForObject(sql, MAPPER, txnId);
    }

    public List<PaymentRow> findByOrderId(int orderId) {
        String sql = SELECT_ALL + "WHERE order_id = ?";
        return jdbc.queryForList(sql, MAPPER, orderId);
    }
}
