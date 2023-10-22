package edu.hw2.task3.connection;

import edu.hw2.task3.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int EXECUTION_SUCCESS_PERIOD = 3;
    private int counterOfExecutions = 0;
    private boolean isOpen = true;

    @Override
    public void execute(String command) {
        if (isClosed()) {
            throw new ConnectionException("Executing a command after closing the connection");
        }
        LOGGER.info("Executing the command - {}", command);
        counterOfExecutions++;

        if (counterOfExecutions % EXECUTION_SUCCESS_PERIOD != 0) {
            LOGGER.info("Failed to execute the command - {}", command);
            throw new ConnectionException("Failed to execute the command - " + command);
        }
        LOGGER.info("Command executed successfully");
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void close() {
        LOGGER.info("Closing the connection");
        if (isOpen) {
            isOpen = false;
            LOGGER.info("The connection successfully closed");
        } else {
            throw new ConnectionException("The connection is already closed");
        }
    }

    private boolean isClosed() {
        return !isOpen;
    }
}
