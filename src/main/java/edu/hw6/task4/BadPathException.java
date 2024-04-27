package edu.hw6.task4;

public class BadPathException extends RuntimeException {
    private static final String DEFAULT_ERROR_MESSAGE = "Invalid path!";

    public BadPathException(String message) {
        super(message);
    }

    public BadPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadPathException(Throwable cause) {
        super(DEFAULT_ERROR_MESSAGE, cause);
    }
}
