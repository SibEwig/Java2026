package com.base.junit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParamsExampleTest {

    @ParameterizedTest(name = "Статус заказа {0}")
    @ValueSource(strings = {"PAID", "READY", "COMPLETED"})
    public void successStatuses(String status) {
        System.out.println(status);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10})
    public void numbersPositive(int number) {
        assertTrue(number > 0);
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "10, 10, 20",
            "0, 0, 0"
    })
    public void addingNumbersShouldReturnsValidResult(int num1, int num2, int result) {
        assertEquals(result, num1 + num2);
    }
}
