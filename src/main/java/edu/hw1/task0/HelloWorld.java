package edu.hw1.task0;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class HelloWorld {
    private final static Logger LOGGER = LogManager.getLogger();

    public void say() {
        LOGGER.info("Hello, world!");
    }
}
