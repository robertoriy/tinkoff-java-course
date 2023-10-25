package edu.hw2.task3.manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;
import edu.hw2.task3.probability.ProbabilityChecker;
import edu.hw2.task3.probability.RandomProbabilityChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultConnectionManager implements ConnectionManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final double DEFAULT_FAILURE_PROBABILITY = 0.8;
    private final ProbabilityChecker faultyConnectionProbability;
    private final double failureProbability;

    public DefaultConnectionManager(double faultyConnectionProbability) {
        this(faultyConnectionProbability, DEFAULT_FAILURE_PROBABILITY);
    }

    public DefaultConnectionManager(double faultyConnectionProbability, double failureProbability) {
        this.faultyConnectionProbability = new RandomProbabilityChecker(faultyConnectionProbability);
        this.failureProbability = failureProbability;
    }

    @Override
    public Connection getConnection() {
        if (faultyConnectionProbability.hasEventOccurred()) {
            LOGGER.info("Faulty Connection sent");
            return new FaultyConnection(failureProbability);
        }
        LOGGER.info("Stable Connection sent");
        return new StableConnection();
    }
}
