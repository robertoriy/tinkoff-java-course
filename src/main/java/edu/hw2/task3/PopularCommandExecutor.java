package edu.hw2.task3;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.exception.ConnectionException;
import edu.hw2.task3.manager.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void tryExecute(String command) {
        for (int i = 1; i <= maxAttempts; i++) {
            LOGGER.info("Trying to get connection and execute the command - {}", command);

            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                break;
            } catch (ConnectionException e) {
                if (i == maxAttempts) {
                    String limitAttemptsError = "Limit of command execution attempts exceeded";
                    LOGGER.info(limitAttemptsError);
                    throw new ConnectionException(limitAttemptsError, e);
                }
            }
            LOGGER.info("The attempt failed, the request is repeated...");
        }
    }
}
