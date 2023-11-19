package edu.hw6.task3;

public class FilterException extends RuntimeException {
    private static final String DEFAULT_ERROR_MESSAGE = "File reading error!";

    public FilterException(String message) {
        super(message);
    }

    public FilterException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilterException(Throwable cause) {
        super(DEFAULT_ERROR_MESSAGE, cause);
    }
}
