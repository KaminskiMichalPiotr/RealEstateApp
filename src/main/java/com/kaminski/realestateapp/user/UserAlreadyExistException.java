package com.kaminski.realestateapp.user;

public class UserAlreadyExistException extends Exception {

    private static String message = "{\"error\":\"User already exist!\"}";

    public UserAlreadyExistException() {
        super(message);
    }
}
