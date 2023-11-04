package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        LOGGER.info("hello");
    }
}
