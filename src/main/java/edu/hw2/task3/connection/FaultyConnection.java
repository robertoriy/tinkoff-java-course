package edu.hw2.task3.connection;

import edu.hw2.task3.exception.ConnectionException;
import edu.hw2.task3.probability.ProbabilityChecker;
import edu.hw2.task3.probability.RandomProbabilityChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ProbabilityChecker failureProbability;
    private boolean isOpen = true;

    public FaultyConnection(double failureProbability) {
        this.failureProbability = new RandomProbabilityChecker(failureProbability);
    }

    @Override
    public void execute(String command) {
        if (isClosed()) {
            throw new IllegalStateException("Executing a command after closing the connection");
        }
        LOGGER.info("Executing the command - {}", command);

        if (failureProbability.hasEventOccurred()) {
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
            throw new IllegalStateException("The connection is already closed");
        }
    }

    private boolean isClosed() {
        return !isOpen;
    }
}
