package edu.hw2.task3.exception;

public class LimitOfExecutionAttemptsExceededException extends RuntimeException {
    public LimitOfExecutionAttemptsExceededException() {
    }

    public LimitOfExecutionAttemptsExceededException(String message) {
        super(message);
    }

    public LimitOfExecutionAttemptsExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public LimitOfExecutionAttemptsExceededException(Throwable cause) {
        super(cause);
    }
}
