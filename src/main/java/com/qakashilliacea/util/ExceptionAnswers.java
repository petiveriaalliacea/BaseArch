package com.qakashilliacea.util;

public class ExceptionAnswers {
    public static String userWithLoginExists(String login) {
        return "User with username=" + login + " already exists";
    }
    public static String cantFindEntityById (String entityName, Long id) {
        return "Cannot find " + entityName + " with id=" + id;
    }
    public static String userNotFoundByUsername (String username) {
        return "Cannot find user with username=" + username;
    }
    public static String incorrectPassword () {
        return "Incorrect password";
    }
}
