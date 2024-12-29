package com.testkafka.springkafka.publicevent;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicEventController {

    private  final  SendMessageEvent sendMessageEvent;

    @GetMapping
    public ResponseEntity<String> sendPublicEvent() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String hostname = inetAddress.getHostName();
        sendMessageEvent.sendTestTopic();
        return ResponseEntity.ok("Hello from " + hostname);
    }
}
