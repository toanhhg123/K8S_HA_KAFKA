package com.testkafka.springkafka.publicevent;

import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Slf4j
@Service
public class SendMessageEvent {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private int messageCount = 0;
    private int messageCommitCount = 0;

    public SendMessageEvent(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTestTopic() {
        String randomMessage = "Random Message " + System.currentTimeMillis();
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("my-topic", randomMessage);
        future.whenComplete(getSendResultThrowableBiConsumer());
    }

    @KafkaListener(topics = "my-topic")
    public void processMessage(String content, @Header(KafkaHeaders.OFFSET) long offset, @Header(KafkaHeaders.GROUP_ID) String groupId) throws InterruptedException {
        Thread.sleep(100);
        messageCommitCount++;
        log.info("Received message commit count: {} with offset {} and group id {} and content {}", messageCommitCount, offset, groupId, content);
    }


    private BiConsumer<SendResult<String, String>, Throwable> getSendResultThrowableBiConsumer() {
        return (result, ex) -> {
            if (ex == null) {
                messageCount++;
                log.info("Sent message : {} at offset: {}", messageCount, result.getRecordMetadata().offset());
            } else {
                log.error("Error while sending message", ex);
            }
        };
    }
}
