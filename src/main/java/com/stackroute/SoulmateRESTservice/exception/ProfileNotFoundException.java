package com.stackroute.SoulmateRESTservice.exception;

public class ProfileNotFoundException extends Exception {
    private String message;

    public ProfileNotFoundException(String message) {
        super();
        this.message = message;
    }

    public ProfileNotFoundException() {
    }
}
