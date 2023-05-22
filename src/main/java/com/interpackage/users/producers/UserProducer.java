package com.interpackage.users.producers;

import com.interpackage.basedomains.dto.UserEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;


@Service
@RequestScope
public class UserProducer {

    private final NewTopic topic;
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public UserProducer(NewTopic topic, KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    } 
    
    public void sendMessage(UserEvent event){
        //Message
        Message<UserEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
