package com.microservices.product.exception;

import com.microservices.product.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ProductServiceExceptionHandler {

  @ExceptionHandler(ProductServiceException.class)
  public ResponseEntity<ErrorResponse> productServiceException(
      ProductServiceException productServiceException) {
    ErrorResponse errorResponse =
        new ErrorResponse()
            .builder()
            .errorCode(productServiceException.getErrorCode())
            .errorMessage(productServiceException.getMessage())
            .shortDescription(productServiceException.getShortDescription())
            .timestamp(LocalDateTime.now())
            .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}
