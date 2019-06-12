package com.aimprosoft.kmb.validator;

import java.util.HashMap;
import java.util.Map;


public class ValidationResult {

    private Map<String, Object> error = new HashMap<>();

    public void addError(String field, Object message) {

        error.put(field, message);
    }

    public boolean hasError() {
        return !error.isEmpty();
    }

    public Map<String, Object> getError() {
        return error;
    }
}
