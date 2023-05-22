package com.interpackage.users.interfaces;

import com.interpackage.basedomains.dto.RouteEvent;
import com.interpackage.users.model.User;

import java.util.List;

public interface EventInterface {

    void sendNotification(User user);
    void sendNotification(List<com.interpackage.basedomains.dto.User> clients, RouteEvent routeEvent);
}
