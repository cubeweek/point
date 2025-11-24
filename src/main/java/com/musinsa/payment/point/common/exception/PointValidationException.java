package com.musinsa.payment.point.common.exception;

public class PointValidationException extends RuntimeException {
    private final String msg;
    public PointValidationException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getErrorMsg() { return this.msg; }
}
