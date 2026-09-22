package com.shawarmashop.tests.db.repository;

import com.shawarmashop.tests.db.Jdbc;
import com.shawarmashop.tests.db.RowMapper;
import com.shawarmashop.tests.db.TestDatabase;
import com.shawarmashop.tests.db.rows.IngredientRow;

import java.util.List;
import java.util.Optional;

public class IngredientRepository {

    private final Jdbc jdbc = TestDatabase.jdbc();

    private static final RowMapper<IngredientRow> MAPPER = resultSet -> IngredientRow.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .unit(resultSet.getString("unit"))
            .minLevel(resultSet.getInt("min_level"))
            .onHand(resultSet.getInt("on_hand"))
            .supplierChannel(resultSet.getString("supplier_channel"))
            .supplierCode(resultSet.getString("supplier_code"))
            .supplierLeadTimeDays(resultSet.getInt("supplier_lead_time_days"))
            .build();

    private static final String SELECT_ALL = """
            SELECT id, name, unit, min_level, on_hand,
                   supplier_channel, supplier_code,
                   supplier_lead_time_days
            FROM ingredients
            """;

    public Optional<IngredientRow> findById(long id) {
        String sql = SELECT_ALL + "WHERE id = ?";
        return jdbc.queryForObject(sql, MAPPER, id);
    }

    public Optional<IngredientRow> findByName(String name) {
        String sql = SELECT_ALL + "WHERE name = ?";
        return jdbc.queryForObject(sql, MAPPER, name);
    }

    public List<IngredientRow> findLowStock() {
        String sql = SELECT_ALL + "WHERE on_hand <= min_level ORDER BY id";
        return jdbc.queryForList(sql, MAPPER);
    }

}
