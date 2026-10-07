package com.shawarmashop.tests.junit;

import com.shawarmashop.tests.partners.PartnerStubs;
import org.junit.jupiter.api.extension.*;

public class PartnerStubExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(PartnerStubExtension.class);
    public static final String KEY = "PartnerStubs";

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        PartnerStubs stubs = extensionContext.getStore(NAMESPACE).get(KEY, PartnerStubs.class);
        if (stubs != null) stubs.resetToDefaults();
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        PartnerStubs stubs = new PartnerStubs();
        stubs.resetToDefaults();
        extensionContext.getStore(NAMESPACE).put(KEY, stubs);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == PartnerStubs.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(KEY, PartnerStubs.class);
    }
}
