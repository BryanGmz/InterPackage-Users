/**
 * Detail package info.
 */
package com.interpackage.users.util;

/**
 * This class contains constants used throughout the application.
 */
public final class Constants {

    /**
     * The API endpoint for users version 1.
     */
    public static final String API_USERS_V1 = "/api/users/v1";


    /**
     * The Role Admin.
     */
    public static final String ROLE_ADMIN = "Admin";

    /**
     * The Role Operator.
     */
    public static final String ROLE_OPERATOR = "Operator";

    /**
     * The Role Client.
     */
    public static final Long ID_ROLE_CLIENT = 3L;
    public static final String ROLE_CLIENT = "Client";
    /**
     * The Role Receptionist.
     */
    public static final String ROLE_RECEPTIONIST = "Receptionist";

    public static final String MESSAGE_NOTIFICATION = "Notification";
    public static final String PENDING_STATE = "Pending";
    public static final String PROCESSED_STATE = "Processed";

    /**
     * Private constructor to prevent instantiation.
     */
    private Constants() {
    }
}

