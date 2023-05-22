package com.interpackage.users.service;

import com.interpackage.basedomains.dto.RouteEvent;
import com.interpackage.basedomains.dto.UserEvent;
import com.interpackage.users.interfaces.EventInterface;
import com.interpackage.users.model.User;
import com.interpackage.users.producers.RouteProducers;
import com.interpackage.users.producers.UserProducer;
import com.interpackage.users.util.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements EventInterface {

    private final UserProducer userProducer;
    private final RouteProducers routeProducers;

    public EventService(
            final UserProducer userProducer,
            final RouteProducers routeProducers) {
        this.userProducer = userProducer;
        this.routeProducers = routeProducers;
    }

    @Override
    public void sendNotification(User user) {
        UserEvent userEvent = new UserEvent();
        userEvent.setMessage(Constants.MESSAGE_NOTIFICATION);
        userEvent.setStatus(Constants.PENDING_STATE);
        userEvent.setUser(
                new com.interpackage.basedomains.dto.User(
                        user.getName(), user.getName(), user.getEmail(), user.getRole().getIdRole().toString()));
        userProducer.sendMessage(userEvent);
    }

    @Override
    public void sendNotification(List<com.interpackage.basedomains.dto.User> clients, RouteEvent routeEvent) {
        routeEvent.setClients(clients);
        routeProducers.sendMessage(routeEvent);
    }
}
