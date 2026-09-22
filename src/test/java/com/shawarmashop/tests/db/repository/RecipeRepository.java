package com.shawarmashop.tests.db.repository;


import com.shawarmashop.tests.db.Jdbc;
import com.shawarmashop.tests.db.RowMapper;
import com.shawarmashop.tests.db.TestDatabase;
import com.shawarmashop.tests.db.rows.RecipeRow;

import java.util.Optional;

public class RecipeRepository {
    private final Jdbc jdbc = TestDatabase.jdbc();

    private static final RowMapper<RecipeRow> RECIPE_MAPPER = resultSet ->
            RecipeRow.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .size(resultSet.getString("size"))
                    .price(resultSet.getDouble("price"))
                    .prepSeconds(resultSet.getInt("prep_seconds"))
                    .description(resultSet.getString("description"))
                    .imageUrl(resultSet.getString("image_url"))
                    .build();

    private static final String SELECT_ALL =
            "SELECT id, name, description, size, price, prep_seconds, image_url FROM recipes";

    public Optional<RecipeRow> findById(long id) {
        String sql = SELECT_ALL + "WHERE id = ?";
        return jdbc.queryForObject(sql, RECIPE_MAPPER, id);
    }

    public Optional<RecipeRow> findByName(String name) {
        String sql = SELECT_ALL + "WHERE name = ?";
        return jdbc.queryForObject(sql, RECIPE_MAPPER, name);
    }

    public long count() {
        String sql = "SELECT COUNT(*) AS c FROM recipes";
        return jdbc.queryForObject(
                sql,
                resultSet -> resultSet.getLong("c"))
                .orElse(0L);
    }
}
