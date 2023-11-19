package edu.hw6.task5;

public class InvalidContentException extends RuntimeException {
    private static final String DEFAULT_ERROR_MESSAGE = "Invalid content!";

    public InvalidContentException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

    public InvalidContentException(String message) {
        super(message);
    }

    public InvalidContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidContentException(Throwable cause) {
        super(DEFAULT_ERROR_MESSAGE, cause);
    }
}
