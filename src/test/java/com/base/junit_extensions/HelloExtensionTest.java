package com.base.junit_extensions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloExtensionTest {

    @Test
    @ExtendWith(LogTestNameExtension.class)
    public void test1() {
        System.out.println("Тест, который называется test1");
        assertEquals(5, 10);
    }

    @Test
    public void test2() {
        System.out.println("Тест, который называется test1");
        assertEquals(5, 10);
    }

    @Test
    @Garage(cars = 5)
    @ExtendWith(GarageParamResolver.class)
    public void test3(UserWithGarage userWithGarage) {
        System.out.println("Тест, который называется test1");
        assertEquals(5, 10);
    }
}
