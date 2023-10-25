package edu.hw2.task3;

import edu.hw2.task3.exception.LimitOfExecutionAttemptsExceededException;
import edu.hw2.task3.manager.FaultyConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MAX_ATTEMPTS = 6;
    private static final double FAILURE_PROBABILITY = 0.7;

    private Main() {
    }

    public static void main(String[] args) {
        var executor = new PopularCommandExecutor(new FaultyConnectionManager(FAILURE_PROBABILITY), MAX_ATTEMPTS);
        try {
            executor.tryExecute("git reset --hard HEAD~2");
        } catch (LimitOfExecutionAttemptsExceededException e) {
            LOGGER.info("{} {} {}", e.getMessage(), e.getCause(), e.getCause().getMessage());
        }
    }
}
