package edu.hw2.task3.manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnectionManager implements ConnectionManager {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Connection getConnection() {
        LOGGER.info("Faulty Connection sent");
        return new FaultyConnection();
    }
}
