package com.kaminski.realestateapp.user;

public class UserAlreadyExistException extends Exception {

    private static String message = "User already exist!";

    public UserAlreadyExistException() {
        super(message);
    }
}
