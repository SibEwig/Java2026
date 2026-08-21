package com.base.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcHelloTest {
    private static final String URL = "jdbc:postgresql://shawarma.threadqa.ru:5433/shawarma_db";
    private static final String USER = "shawarma_reader";
    private static final String PASSWORD = "shawara_cucumber_nadzor";

    @Test
    public void test1() {
        String sql = "SELECT * FROM recipes WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, 15);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    RecipeRow recipeRow = RecipeRow.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .size(resultSet.getString("size"))
                            .price(resultSet.getDouble("price"))
                            .prepSeconds(resultSet.getInt("prep_seconds"))
                            .build();
                    System.out.println("Достали " + recipeRow);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
