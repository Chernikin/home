package com.aimprosoft.kmb.validator;

import java.util.HashMap;
import java.util.Map;


public class ValidationResult {

    private Map<String, String> errorMessage = new HashMap<>();

    public void addErrorMessage(String field, String message) {

        errorMessage.put(field, message);
    }

    public boolean hasError() {
        return !errorMessage.isEmpty();
    }

    public Map<String, String> getErrorMessage() {
        return errorMessage;
    }
}
