package com.stackroute.SoulmateRESTservice.exception;

public class ProfileAlreadyExistsException extends Exception {
    private String message;

    public ProfileAlreadyExistsException() {
    }

    public ProfileAlreadyExistsException(String message) {
        super();
        this.message = message;
    }
}
