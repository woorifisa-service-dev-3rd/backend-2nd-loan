package dev.service.cloud.loan.customexception;
public class NoRecommendedProductsException extends RuntimeException {
    public NoRecommendedProductsException(String message) {
        super(message);
    }
}

