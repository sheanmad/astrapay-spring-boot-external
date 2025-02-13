package com.astrapay.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomCheckedException extends RuntimeException {
    private int errorHttpStatus;
    private String errorStatus;
    public CustomCheckedException(String message, int errorHttpStatus, String errorStatus) {
        super(message);
        this.errorHttpStatus = errorHttpStatus;
        this.errorStatus = errorStatus;
    }
}