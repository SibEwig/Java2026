package com.base.junit_extensions.hw;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(TestCartExtension.class)
class TestCartTest {

    @Test
    void cartIsInjectedFresh(TestCart cart) {
        assertThat(cart.getId()).isPositive();
        assertThat(cart.getItems()).isEmpty();
    }

    @Test
    void shouldContainAddedItems(TestCart cart) {
        cart.add("shawarma");
        cart.add("cucumber");

        assertThat(cart.getItems())
                .containsExactly("shawarma", "cucumber");
    }
}
