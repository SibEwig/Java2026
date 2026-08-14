package com.base.assertj;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CollectionsAssertjTest {

    @Test
    public void test1() {
        List<String> statuses = List.of("PAID", "CREATED", "PREPARING");

        assertThat(statuses)
                .as("Список с доступными статусами")
                .hasSize(3)
                .doesNotContain("DONE")
                .contains("PAID")
                .allMatch(x -> x.toUpperCase().equals(x));
    }

    @Test
    public void test2() {
//        List<String> statuses = List.of("PAID", "CREATED", "PREPARING");
//        assertSoftly(x -> {
//            x.assertThat(statuses).hasSize(4);
//            x.assertThat(statuses).contains("DONE");
//            x.assertThat(statuses).doesNotContain("CANCELLED");
//        });
        assertThatThrownBy(() -> Integer.parseInt("not-a-number")).isInstanceOf(NumberFormatException.class);
    }

}
