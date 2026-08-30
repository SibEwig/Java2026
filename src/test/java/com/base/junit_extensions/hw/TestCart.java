package com.base.junit_extensions.hw;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
class TestCart {
    private final long id;
    private final List<String> items = new ArrayList<>();

    TestCart(long id) {
        this.id = id;
    }

    void add(String dish) {
        items.add(dish);
    }

    void clear() {
        items.clear();
    }
}
