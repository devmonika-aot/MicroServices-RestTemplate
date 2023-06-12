package com.microservices.order.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class OrderCustomException extends RuntimeException {
    private String errorMessage;
    private long errorCode;
    private String errorDescription;

    public OrderCustomException(String errorMessage, long errorCode, String errorDescription) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public OrderCustomException(String message, String errorMessage, long errorCode, String errorDescription) {
        super(message);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public OrderCustomException(String message, Throwable cause, String errorMessage, long errorCode, String errorDescription) {
        super(message, cause);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public OrderCustomException(Throwable cause, String errorMessage, long errorCode, String errorDescription) {
        super(cause);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public OrderCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorMessage, long errorCode, String errorDescription) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public OrderCustomException() {

    }
}
