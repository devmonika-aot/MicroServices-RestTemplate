package com.microservices.order.exception;

import org.springframework.http.HttpStatus;

public class RedirectionException extends  RuntimeException{
    public RedirectionException(HttpStatus statusCode, String statusText) {
        super("Redirection error: " + statusCode + " " + statusText);
    }
}
