package com.shawarmashop.tests.e2e;

import com.shawarmashop.tests.auth.ApiClient;
import com.shawarmashop.tests.auth.Users;
import com.shawarmashop.tests.dto.CreateOrderRequest;
import com.shawarmashop.tests.dto.OrderResponse;
import com.shawarmashop.tests.dto.PayRequest;
import com.shawarmashop.tests.junit.KafkaTest;
import com.shawarmashop.tests.kafka.KafkaTestBus;
import com.shawarmashop.tests.kafka.OrderEventsTopic;
import com.shawarmashop.tests.kafka.events.OrderEvent;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@KafkaTest
public class KafkaExamplesTest {
    private final ApiClient owner = ApiClient.asUser(Users.OWNER);

    @Test
    public void test1(KafkaTestBus kafkaTestBus) {
        OrderEventsTopic ordersTopic = kafkaTestBus.orders();
        OrderResponse order = owner
                .orders()
                .create(CreateOrderRequest.of(1, 1, "CARD"))
                .success();

        OrderEvent orderEvent = ordersTopic.awaitPlaced(order.getId());
        assertThat(orderEvent.getOrderId()).isEqualTo(1);
        assertThat(orderEvent.getQty()).isEqualTo(1);
        assertThat(orderEvent.getTotalPrice()).isPositive();

        owner.payments()
                .pay(order.getId(), UUID.randomUUID().toString(), PayRequest.builder().build())
                .assertStatus(202);

        OrderEvent orderPaidEvent = ordersTopic.awaitPaid(order.getId());
        assertThat(orderPaidEvent.getTxnId()).isNotBlank();
        assertThat(orderEvent.getEtaAt()).isNotNull();
    }
}
