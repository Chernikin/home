package com.aimprosoft.kmb.exceptions;

public class ConnectionException extends RepositoryException {


    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
