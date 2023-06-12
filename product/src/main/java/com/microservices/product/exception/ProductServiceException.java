package com.microservices.product.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductServiceException extends RuntimeException {
  private String message;
  private long errorCode;

  private String shortDescription;

  public ProductServiceException() {}

  public ProductServiceException(String message, long errorCode, String shortDescription) {
    this.message = message;
    this.errorCode = errorCode;
    this.shortDescription = shortDescription;
  }

  public ProductServiceException(
      String message, String message1, long errorCode, String shortDescription) {
    super(message);
    this.message = message1;
    this.errorCode = errorCode;
    this.shortDescription = shortDescription;
  }

  public ProductServiceException(
      String message, Throwable cause, String message1, long errorCode, String shortDescription) {
    super(message, cause);
    this.message = message1;
    this.errorCode = errorCode;
    this.shortDescription = shortDescription;
  }

  public ProductServiceException(
      Throwable cause, String message, long errorCode, String shortDescription) {
    super(cause);
    this.message = message;
    this.errorCode = errorCode;
    this.shortDescription = shortDescription;
  }

  public ProductServiceException(
      String message,
      Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace,
      String message1,
      long errorCode,
      String shortDescription) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.message = message1;
    this.errorCode = errorCode;
    this.shortDescription = shortDescription;
  }
}
