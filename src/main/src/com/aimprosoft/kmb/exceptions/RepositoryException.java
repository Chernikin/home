package com.aimprosoft.kmb.exceptions;

public class RepositoryException extends Exception {

    public RepositoryException() {

    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
