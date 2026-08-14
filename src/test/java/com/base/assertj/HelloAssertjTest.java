package com.base.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloAssertjTest {

    @Test
    public void test1() {
        int quantity = 5;

        assertThat(quantity).isPositive();
        assertThat(quantity).isGreaterThan(1);
        assertThat(quantity).isEqualTo(5);
        assertThat(quantity).isLessThan(10);
    }

    @Test
    public void test2() {
        String jwtToken = "Bearer asd.btr.sdfega";

        assertThat(jwtToken).isNotBlank();
        assertThat(jwtToken).startsWith("Bearer");
        assertThat(jwtToken).contains("asd");
    }

    @Test
    public void test3() {
        String status = "PAID";

        assertThat(status)
                .isNotNull()
                .isEqualTo("PAID")
                .hasSize(4);
    }
}
