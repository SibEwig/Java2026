package com.shawarmashop.tests.partners.payments;

import com.shawarmashop.tests.partners.payments.dto.*;
import com.shawarmashop.tests.partners.wiremock.WiremockAdminClient;
import com.shawarmashop.tests.partners.wiremock.WiremockStubBase;
import com.shawarmashop.tests.support.Json;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.shawarmashop.tests.partners.wiremock.StubBuilder.post;

public class PaymentsStubs extends WiremockStubBase {

    private static final String CHARGE_PATH = "/payments/charge";
    private static final String CURRENCY_RUB = "RUB";
    private static final BigDecimal DEFAULT_FEE = new BigDecimal("5.0");

    public PaymentsStubs(WiremockAdminClient admin) {
        super(admin);
    }

    public void responseSuccess(String txnId) {
        admin.addMapping(post(CHARGE_PATH).willReturnJson(
                200,
                ChargeResponseDto.builder()
                        .fee(ChargeMoney.builder()
                                .currency(CURRENCY_RUB)
                                .value(DEFAULT_FEE)
                                .build())
                        .txnId(txnId)
                        .message("OK")
                        .processedAt(Instant.now())
                        .status(ChargeStatus.SUCCEED)
                        .build()
        ));
    }

    public void responseFailed(String reason) {
        admin.addMapping(post(CHARGE_PATH).willReturnJson(
                200,
                ChargeResponseDto.builder()
                        .fee(ChargeMoney.builder()
                                .currency(CURRENCY_RUB)
                                .value(BigDecimal.ZERO)
                                .build())
                        .txnId(shortId())
                        .message(reason)
                        .processedAt(Instant.now())
                        .status(ChargeStatus.FAILED)
                        .build()
        ));
    }

    public void serviceUnavailable() {
        admin.addMapping(post(CHARGE_PATH).willReturnJson(
                503,
                PaymentsErrorDto.builder()
                        .error("service_unavailable")
                        .message("Что-то пошло не так")
                        .attempt(1)
                        .build()
        ));
    }

    public List<ChargeRequestDto> recordedRequests() {
        Map<String, Object> filter = Map.of("method", "POST", "url", CHARGE_PATH);
        return admin.findRequests(filter).stream()
                .map(x -> x.get("body"))
                .filter(Objects::nonNull)
                .map(x -> Json.fromJson(x.toString(), ChargeRequestDto.class))
                .toList();
    }
}
