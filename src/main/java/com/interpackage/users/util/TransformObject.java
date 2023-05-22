package com.interpackage.users.util;

import com.interpackage.basedomains.dto.User;

import java.util.List;

public class TransformObject {

    public static List<User> getListUserBaseDomain(List<com.interpackage.users.model.User> users, String role){
        return users.stream()
                .map(user ->
                        new User(
                                user.getName(),
                                user.getName(),
                                user.getEmail(),
                                role))
                .toList();
    }
}
