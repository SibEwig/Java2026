package com.base.junit_extensions;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Optional;

public class LogTestNameExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        String displayName = extensionContext.getDisplayName();
        System.out.println("BeforeEach название тестового метода " + displayName);
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        String displayName = extensionContext.getDisplayName();
        Optional<Throwable> executionException = extensionContext.getExecutionException();
        if (executionException.isPresent()) System.out.println("Тест упал: " + displayName);

    }
}
