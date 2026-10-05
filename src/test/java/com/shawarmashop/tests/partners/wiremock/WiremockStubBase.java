package com.shawarmashop.tests.partners.wiremock;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class WiremockStubBase {

    protected final WiremockAdminClient admin;

    protected static String shortId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
