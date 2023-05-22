package com.interpackage.users.producers;

import com.interpackage.basedomains.dto.RouteEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class RouteProducers {

    private final NewTopic routeTopic;
    private final KafkaTemplate<String, RouteEvent> kafkaTemplate;

    public RouteProducers(NewTopic routeTopic, KafkaTemplate<String, RouteEvent> kafkaTemplate) {
        this.routeTopic = routeTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(RouteEvent event){
        //Message
        Message<RouteEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, routeTopic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
