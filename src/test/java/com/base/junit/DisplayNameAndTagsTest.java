package com.base.junit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Примеры тестов с аннотациями @DisplayName, @Tag, Disabled")
public class DisplayNameAndTagsTest {

    @Test
    @Tag("smoke")
    @DisplayName("Смоук проверка: сумма работает")
    public void test1() {
        assertEquals(4, 2 + 2);
    }

    @Test
    @Tag("regress")
    @DisplayName("Долгая проверка: умножение работает")
    public void test2() {
        assertEquals(20, 4 * 5);
    }

    @Test
    @Disabled("Сообщение")
    public void test3() {

    }
}
