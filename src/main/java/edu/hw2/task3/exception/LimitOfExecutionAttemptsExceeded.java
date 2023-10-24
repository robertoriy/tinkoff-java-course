package edu.hw2.task3.exception;

public class LimitOfExecutionAttemptsExceeded extends RuntimeException {
    public LimitOfExecutionAttemptsExceeded() {
    }

    public LimitOfExecutionAttemptsExceeded(String message) {
        super(message);
    }

    public LimitOfExecutionAttemptsExceeded(String message, Throwable cause) {
        super(message, cause);
    }

    public LimitOfExecutionAttemptsExceeded(Throwable cause) {
        super(cause);
    }
}
