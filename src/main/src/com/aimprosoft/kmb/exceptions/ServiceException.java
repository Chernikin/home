package com.aimprosoft.kmb.exceptions;

import java.util.List;

public class ServiceException extends ApplicationException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
