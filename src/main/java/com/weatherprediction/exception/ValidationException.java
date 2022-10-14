package com.weatherprediction.exception;

public class ValidationException extends ApiException {

    public ValidationException(String msg, Object... params) {
        super(msg, params);
    }
}
