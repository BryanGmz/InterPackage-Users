package com.interpackage.users.consumer;

import com.interpackage.basedomains.dto.RouteEvent;
import com.interpackage.basedomains.dto.TrackingEvent;
import com.interpackage.basedomains.dto.User;
import com.interpackage.users.service.EventService;
import com.interpackage.users.service.UserService;
import com.interpackage.users.util.Constants;
import com.interpackage.users.util.TransformObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
public class UserConsumer {

    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;

    @KafkaListener(
            topics = "${spring.kafka.route-topic.name}",
            groupId = "${spring.kafka.route-consumer.group-id}")
    public void consume(final RouteEvent event) {
        eventService.sendNotification(
                TransformObject
                        .getListUserBaseDomain(
                                userService.getAllClients(),
                                Constants.ROLE_CLIENT),
                event);
    }
}
