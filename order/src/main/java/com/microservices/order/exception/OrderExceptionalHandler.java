package com.microservices.order.exception;

import com.microservices.order.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class OrderExceptionalHandler {

  @ExceptionHandler(OrderCustomException.class)
  public ResponseEntity<ErrorResponse> orderHandler(OrderCustomException exe) {
    ErrorResponse obj =
        ErrorResponse.builder()
            .errorCode(exe.getErrorCode())
            .shortDescription(exe.getErrorDescription())
            .errorMessage(exe.getErrorMessage())
            .timestamp(LocalDateTime.now())
            .build();
    return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
  }
}
