package edu.hw2.task3.manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultConnectionManager implements ConnectionManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int FAULTY_CONNECTION_PERIOD = 4;
    private int requestCounter = 0;

    @Override
    public Connection getConnection() {
        requestCounter++;
        if (requestCounter % FAULTY_CONNECTION_PERIOD == 0) {
            LOGGER.info("Faulty Connection sent");
            return new FaultyConnection();
        }
        LOGGER.info("Stable Connection sent");
        return new StableConnection();
    }
}
