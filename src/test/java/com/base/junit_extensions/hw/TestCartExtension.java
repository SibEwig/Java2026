package com.base.junit_extensions.hw;

import org.junit.jupiter.api.extension.*;

class TestCartExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    private static final String TEST_CART_KEY = "testCart";

    @Override
    public void beforeEach(ExtensionContext context) {
        store(context).put(TEST_CART_KEY, new TestCart(1));
    }

    @Override
    public void afterEach(ExtensionContext context) {
        store(context).get(TEST_CART_KEY, TestCart.class).clear();
        System.out.println("Тестовая корзина очищена");
    }

    @Override
    public boolean supportsParameter(ParameterContext pc, ExtensionContext ec) {
        return pc.getParameter().getType() == TestCart.class;
    }

    @Override
    public Object resolveParameter(ParameterContext pc, ExtensionContext ec) {
        return store(ec).get(TEST_CART_KEY, TestCart.class);
    }

    private static ExtensionContext.Store store(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(TestCartExtension.class));
    }
}
