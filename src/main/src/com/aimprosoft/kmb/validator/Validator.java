package com.aimprosoft.kmb.validator;

public interface Validator<T> {

    ValidationResult validate(T object, String field);

}
