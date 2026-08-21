package com.base.allure;

import io.qameta.allure.Step;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HelloAllureTest {

    @Test
    public void test1() {
        loginAs((new Faker()).internet().emailAddress());
        int i = placeOrder(5, 2);
        assertThat(i).isGreaterThan(100);
    }

    @Step("Авторизация под {0}")
    public void loginAs(String email) {
        //
    }

    @Step("Создаём заказ с рецептом {0} и количеством {1}")
    public int placeOrder(int recipeId, int qty) {
        return 10;
    }
}
