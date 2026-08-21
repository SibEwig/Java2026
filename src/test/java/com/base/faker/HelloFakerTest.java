package com.base.faker;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

public class HelloFakerTest {

    private final Faker faker = new Faker(new Locale("ru"));

    @Test
    public void test1() {
        System.out.println(faker.address().cityName());

        int i = faker.number().numberBetween(1, 5);
    }

    @Test
    public void test2() {
        TestUser user = TestUserFactory.newUser().build();
        System.out.println(user);
    }
}
