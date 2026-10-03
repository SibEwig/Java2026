package com.shawarmashop.tests.junit;

import com.shawarmashop.tests.kafka.KafkaTestBus;
import org.junit.jupiter.api.extension.*;

public class KafkaTestBusExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(KafkaTestBusExtension.class);
    public static final String KEY = "KafkaTestBus";

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        KafkaTestBus kafkaTestBus = extensionContext.getStore(NAMESPACE).get(KEY, KafkaTestBus.class);
        if (kafkaTestBus != null) kafkaTestBus.close();
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        extensionContext.getStore(NAMESPACE).put(KEY, new KafkaTestBus());
    }

    @Override
    public boolean supportsParameter(
            ParameterContext parameterContext,
            ExtensionContext extensionContext
    ) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == KafkaTestBus.class;
    }

    @Override
    public Object resolveParameter(
            ParameterContext parameterContext,
            ExtensionContext extensionContext
    ) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(KEY, KafkaTestBus.class);
    }
}
