package com.stackroute.SoulmateRESTservice.exception;

public class Exception extends Throwable {
    private String message3;

    public Exception() {

    }

    public Exception(String message) {
        super();
        this.message3 = message;
    }
}