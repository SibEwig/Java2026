package com.shawarmashop.tests.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.shawarmashop.tests.support.Json;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public abstract class KafkaTopic<T> implements AutoCloseable {

    protected final String topicName;
    protected final Class<T> payloadType;

    private final KafkaConsumer<String, String> consumer;
    private final List<T> recorded = new ArrayList<>();

    protected KafkaTopic(String topicName, Class<T> payloadType, String groupId) {
        this.topicName = topicName;
        this.payloadType = payloadType;
        this.consumer = new KafkaConsumer<>(KafkaConfig.consumerProps(groupId));
        consumer.subscribe(List.of(topicName));
        awaitAssignment(Duration.ofSeconds(15));

        Set<TopicPartition> assignment = consumer.assignment();
        consumer.seekToEnd(assignment);

        for (TopicPartition topicPartition : assignment) {
            consumer.position(topicPartition);
        }
    }
    
    public T awaitMessage(String type, Predicate<T> filter, Duration duration) {
        long deadline = System.nanoTime() + duration.toNanos();
        while (System.nanoTime() < deadline) {
            ConsumerRecords<String, String> batch = consumer.poll(Duration.ofMillis(250));
            for (ConsumerRecord<String, String> record : batch) {
                System.out.printf(
                        "Kafka record=topic=%s, partition=%s, offset=%s, key=%s, value=%s%n",
                        record.topic(),
                        record.partition(),
                        record.offset(),
                        record.key(),
                        record.value()
                );
                JsonNode tree = Json.tree(record.value());

                if (!type.equals(tree.path("type").asText())) {
                    continue;
                }

                T payload = parsePayload(tree);
                recorded.add(payload);

                if (filter.test(payload)) {
                    System.out.printf("Получили %s из %s и %s%n", type, topicName, payload);
                    return payload;
                }
            }
        }

        throw new AssertionError("За " + duration
                + " не пришло сообщение type=" + type
                + "в топик " + topicName
                + ". Распарсенные сообщения: " + recorded
        );
    }

    @Override
    public void close() {
        consumer.close();
    }

    private T parsePayload(JsonNode node) {
        return Json.MAPPER.convertValue(node.path("payload"), payloadType);
    }

    private void awaitAssignment(Duration duration) {
        long deadline = System.nanoTime() + duration.toNanos();
        while (consumer.assignment().isEmpty() && System.nanoTime() < deadline) {
            consumer.poll(Duration.ofMillis(250));
        }
        if (consumer.assignment().isEmpty()) {
            throw new IllegalStateException(("Consumer не получил партиции топика '%s' за %s. " +
                    "Проверь bootstrap сервер и наличие топика").formatted(topicName, duration));
        }
        System.out.println("Consumer получил assignment для " + topicName + " " + consumer.assignment());
    }
}
