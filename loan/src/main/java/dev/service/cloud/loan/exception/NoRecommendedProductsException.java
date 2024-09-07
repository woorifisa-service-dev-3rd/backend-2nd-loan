package dev.service.cloud.loan.exception;


public class NoRecommendedProductsException extends RuntimeException {
    private ErrorCode errorCode;

    public NoRecommendedProductsException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}