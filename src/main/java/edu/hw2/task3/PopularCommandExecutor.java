package edu.hw2.task3;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.manager.ConnectionManager;
import edu.hw2.task3.retry.LimitedRetry;
import edu.hw2.task3.retry.Retry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final Retry retry;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        retry = new LimitedRetry.LimitedRetryBuilder().maxAttempts(maxAttempts).build();
    }

    public void tryExecute(String command) {
        retry.execute(() -> {
            LOGGER.info("Trying to get connection and execute the command - {}", command);
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
            }
        });
    }
}
