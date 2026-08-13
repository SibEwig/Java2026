package com.base.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelloJunitTest {

    @Test
    public void twoPlusTwoShouldBeFour() {
        int a = 2;
        int b = 2;
        int result = a + b;
        assertEquals(4, result);
    }

    @Test
    public void wordShouldContainsAnotherWord() {
        String word = "Computer";
        boolean condition = word.contains("uter");
        assertTrue(condition);
    }
}
