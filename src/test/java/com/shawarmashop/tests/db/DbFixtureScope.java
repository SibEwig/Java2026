package com.shawarmashop.tests.db;

import lombok.RequiredArgsConstructor;

import java.util.ArrayDeque;
import java.util.Deque;

@RequiredArgsConstructor
public class DbFixtureScope {

    private final Jdbc jdbc;
    private final Deque<Cleanup> tracked = new ArrayDeque<>();


    public void track(String description, Runnable cleanup) {
        tracked.push(new Cleanup(description, cleanup));
    }

    public void track(String table, long id) {
        track(table + " id=" + id,
                () -> jdbc.update(
                        "DELETE FROM " + table + " WHERE id = ?",
                        id
                )
        );
    }

    public void cleanup() {
        while (!tracked.isEmpty()) {
            Cleanup pop = tracked.pop();
            try {
                pop.getAction().run();
            } catch (RuntimeException e) {
                System.out.println("DbFixture cleanup упал для " + pop.getDescription() + "\n" + e.getMessage());
            }
        }
    }

    public int size() {
        return tracked.size();
    }
}
