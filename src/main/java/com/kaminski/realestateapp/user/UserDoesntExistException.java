package com.kaminski.realestateapp.user;

public class UserDoesntExistException extends Exception {

    private static String message = "{\"error\":\"User doesn't exist!\"}";

    public UserDoesntExistException() {
        super(message);
    }
}
