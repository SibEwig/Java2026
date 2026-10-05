package com.shawarmashop.tests.partners.bread;

import com.shawarmashop.tests.partners.bread.dto.BreadConfirmationDto;
import com.shawarmashop.tests.partners.bread.dto.BreadErrorDto;
import com.shawarmashop.tests.partners.bread.dto.BreadOrderDto;
import com.shawarmashop.tests.partners.wiremock.WiremockAdminClient;
import com.shawarmashop.tests.partners.wiremock.WiremockStubBase;
import com.shawarmashop.tests.support.Json;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.shawarmashop.tests.partners.wiremock.StubBuilder.post;

public class BreadStubs extends WiremockStubBase {

    private static final String GRPC_PATH = "/BreadBakery/orderBatch";

    public BreadStubs(WiremockAdminClient admin) {
        super(admin);
    }

    public void respondWith(BreadConfirmationDto confirmationDto) {
        admin.addMapping(post(GRPC_PATH).willReturnJson(200, confirmationDto));
    }

    public void respondUnavailable() {
        admin.addMapping(post(GRPC_PATH).willReturnJson(502, BreadErrorDto
                .builder()
                .error("bad_gateway")
                .message("Bread bakery service is unavailable")
                .occurredAt(Instant.now())
                .build()
        ));
    }

    public List<BreadOrderDto> recordedCalls() {
        Map<String, Object> filter = Map.of("method", "POST", "url", GRPC_PATH);
        return admin.findRequests(filter).stream()
                .map(x -> x.get("body"))
                .filter(Objects::nonNull)
                .map(x -> Json.fromJson(x.toString(), BreadOrderDto.class))
                .toList();
    }
}
