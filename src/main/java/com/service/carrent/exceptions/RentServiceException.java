package com.service.carrent.exceptions;

public class RentServiceException extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public RentServiceException(String message, String code) {
        super(message);
        this.code = code;
    }
}
