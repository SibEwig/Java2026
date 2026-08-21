package com.base.jdbc;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcRowMapperTest {

    private static final String URL = "jdbc:postgresql://shawarma.threadqa.ru:5433/shawarma_db";
    private static final String USER = "shawarma_reader";
    private static final String PASSWORD = "shawara_cucumber_nadzor";

    private static final RowMapper<RecipeRow> RECIPE_MAPPER = resultSet ->
            RecipeRow.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .size(resultSet.getString("size"))
                    .price(resultSet.getDouble("price"))
                    .prepSeconds(resultSet.getInt("prep_seconds"))
                    .build();

    @SneakyThrows
    @Test
    public void test1() {
        String sql = "SELECT * FROM recipes";
        List<RecipeRow> recipes = queryTwo(sql, RECIPE_MAPPER);
        System.out.println(recipes);
    }

    @SneakyThrows
    private static <T> T queryAll(String sql, RowMapper<T> mapper, Object... params) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }
                return mapper.mapRow(resultSet);
            }
        }
    }

    @SneakyThrows
    private static <T> List<T> queryTwo(String sql, RowMapper<T> mapper, Object... params) {
        List<T> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(mapper.mapRow(resultSet));
                }
                return result;
            }
        }
    }
}
