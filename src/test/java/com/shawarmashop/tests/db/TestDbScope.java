package com.shawarmashop.tests.db;

public class TestDbScope {
    private static final ThreadLocal<DbFixtureScope> SCOPE =
            ThreadLocal.withInitial(() -> new DbFixtureScope(TestDatabase.jdbc()));

    public static DbFixtureScope current() {
        return SCOPE.get();
    }

    public static void cleanup() {
        SCOPE.get().cleanup();
        SCOPE.remove();
    }
}
