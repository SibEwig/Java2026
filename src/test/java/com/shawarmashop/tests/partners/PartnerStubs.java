package com.shawarmashop.tests.partners;

import com.shawarmashop.tests.env.TestEnvironment;
import com.shawarmashop.tests.partners.bread.BreadStubs;
import com.shawarmashop.tests.partners.meat.MeatStubs;
import com.shawarmashop.tests.partners.payments.PaymentsStubs;
import com.shawarmashop.tests.partners.reviews.ReviewsStubs;
import com.shawarmashop.tests.partners.wiremock.WiremockAdminClient;

public final class PartnerStubs {
    private final WiremockAdminClient restAdmin;
    private final WiremockAdminClient grpcAdmin;

    public PartnerStubs() {
        restAdmin = new WiremockAdminClient(TestEnvironment.INSTANCE.wiremockRestBaseUrl());
        grpcAdmin = new WiremockAdminClient(TestEnvironment.INSTANCE.wiremockGrpcBaseUrl());
    }

    public void resetToDefaults() {
        restAdmin.resetToDefaults();
        grpcAdmin.resetToDefaults();
        restAdmin.clearRecorded();
        grpcAdmin.clearRecorded();
    }

    public PaymentsStubs payments() {
        return new PaymentsStubs(restAdmin);
    }

    public ReviewsStubs reviews() {
        return new ReviewsStubs(restAdmin);
    }

    public MeatStubs meat() {
        return new MeatStubs(restAdmin);
    }

    public BreadStubs bread() {
        return new BreadStubs(grpcAdmin);
    }
}
