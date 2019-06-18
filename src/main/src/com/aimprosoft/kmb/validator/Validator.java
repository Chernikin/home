package com.aimprosoft.kmb.validator;

import com.aimprosoft.kmb.exceptions.ValidationException;

public interface Validator<T> {

    ValidationResult validate(T object, T field) throws ValidationException;

}
