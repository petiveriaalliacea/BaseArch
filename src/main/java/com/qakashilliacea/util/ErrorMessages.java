package com.qakashilliacea.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessages {
    public static String userWithLoginExists(String login) {
        return "User with username=" + login + " already exists";
    }

    public static String cantFindEntityById(Object object, Long id) {
        return "Cannot find " + object.getClass().getName() + " with id=" + id;
    }

    public static String cantFindEntityByUserId(Object object, Long userId) {
        return "Cannot find " + object.getClass().getName() + " with user id=" + userId;
    }

    public static String userNotFoundByUsername(String username) {
        return "Cannot find user with username=" + username;
    }

    public static String incorrectPassword() {
        return "Incorrect password";
    }

    public static String requiredFieldIsEmpty(String fieldName) {
        return "Field " + fieldName + " is required";
    }
}
