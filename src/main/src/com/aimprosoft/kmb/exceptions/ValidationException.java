package com.aimprosoft.kmb.exceptions;

import java.util.Map;

public class ValidationException extends ServiceException {

    private Map<String, Object> errors;

    public ValidationException(Map<String, Object> errors) {
        this.errors = errors;
    }

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
