package edu.hw2.task3.retry;

import edu.hw2.task3.exception.LimitOfExecutionAttemptsExceeded;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LimitedRetry implements Retry {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int DEFAULT_MAX_ATTEMPTS = 5;
    private final int maxAttempts;

    private LimitedRetry(LimitedRetryBuilder builder) {
        this.maxAttempts = builder.maxAttempts;
    }

    @Override
    public void execute(Runnable operation) {
        for (int i = 1; i <= maxAttempts; i++) {
            try {
                operation.run();
                return;
            } catch (Exception e) {
                LOGGER.info("The attempt failed");
                if (i == maxAttempts) {
                    throw new LimitOfExecutionAttemptsExceeded("Limit of command execution attempts exceeded", e);
                }
                LOGGER.info("The request is repeated...");
            }
        }
    }

    public static class LimitedRetryBuilder {
        private int maxAttempts;

        public LimitedRetryBuilder() {
            this(DEFAULT_MAX_ATTEMPTS);
        }

        public LimitedRetryBuilder(int maxAttempts) {
            this.maxAttempts = maxAttempts;
        }

        public LimitedRetryBuilder maxAttempts(int maxAttempts) {
            this.maxAttempts = maxAttempts;
            return this;
        }

        public LimitedRetry build() {
            return new LimitedRetry(this);
        }
    }
}
