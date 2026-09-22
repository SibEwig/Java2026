package com.shawarmashop.tests.db.repository;

import com.shawarmashop.tests.db.Jdbc;
import com.shawarmashop.tests.db.RowMapper;
import com.shawarmashop.tests.db.TestDatabase;
import com.shawarmashop.tests.db.rows.EventLogRow;

import java.util.List;
import java.util.Optional;

public class EventLogRepository {

    private final Jdbc jdbc = TestDatabase.jdbc();

    private static final RowMapper<EventLogRow> MAPPER = resultSet -> EventLogRow.builder()
            .id(resultSet.getLong("id"))
            .ts(resultSet.getTimestamp("ts").toInstant())
            .payload(resultSet.getString("payload"))
            .type(resultSet.getString("type"))
            .build();

    private static final String SELECT_ALL = """
            SELECT id, ts, payload, type FROM event_log
            """;

    public Optional<EventLogRow> findById(long id) {
        String sql = SELECT_ALL + "WHERE id = ?";
        return jdbc.queryForObject(sql, MAPPER, id);
    }

    public List<EventLogRow> recent(int limit) {
        String sql = SELECT_ALL + "ORDER BY ts DESC LIMIT ?";
        return jdbc.queryForList(sql, MAPPER, limit);
    }

    public List<EventLogRow> findByType(String type, int limit) {
        String sql = SELECT_ALL + "WHERE type = ? ORDER BY ts DESC LIMIT ?";
        return jdbc.queryForList(sql, MAPPER, type, limit);
    }
}
