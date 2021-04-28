package com.stackroute.SoulmateRESTservice.exception;

import com.stackroute.SoulmateRESTservice.exception.ProfileAlreadyExistsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message1}")
    private String message1;

    @Value(value = "${data.exception.message2}")
    private String message2;

    @Value(value = "${data.exception.message3}")
    private String message3;

    @ExceptionHandler(value = ProfileAlreadyExistsException.class)
    public ResponseEntity<String> profileAlreadyExistsException(ProfileAlreadyExistsException profileAlreadyExistsException) {
        return new ResponseEntity<String>(message1, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ProfileNotFoundException.class)
    public ResponseEntity<String> profileNotFoundException(ProfileNotFoundException profileNotFoundException) {
        return new ResponseEntity<String>(message2, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
        return new ResponseEntity<>(message3, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
