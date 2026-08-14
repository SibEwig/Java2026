package com.base.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LifecycleTest {

    private int counter;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before all");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("Сброс счётчика до дефолтного значения");
        counter = 10;
    }

    @AfterEach
    public void tearDown() {
        System.out.println("After each");
    }

    @Test
    public void firstTest() {
        System.out.println("Это первый тест");
        counter += 5;
        assertEquals(15, counter);
    }

    @Test
    public void secondTest() {
        System.out.println("Это второй тест");
        assertEquals(10, counter);
    }

    @Test
    public void parsingInvalidValueShouldTrowsNumberFormatException() {
        assertThrows(
                NumberFormatException.class,
                () -> Integer.parseInt("nan")
        );
    }
}
