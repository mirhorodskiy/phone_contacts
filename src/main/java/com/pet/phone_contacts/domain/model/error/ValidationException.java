package com.pet.phone_contacts.domain.model.error;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ValidationException extends RuntimeException {

    private HttpStatus httpStatus;


    public ValidationException(String message) {
        super(message);
        this.httpStatus = HttpStatus.FORBIDDEN;
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
