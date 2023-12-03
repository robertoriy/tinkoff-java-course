package edu.hw8.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static void main(String[] args) {
        LOGGER.info("Hello");

        try (FixedThreadPool threadPool = FixedThreadPool.of(4)) {
            threadPool.execute(() -> {
                LOGGER.info("----task1");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            threadPool.execute(() -> {
                LOGGER.info("----task2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            threadPool.start();
            threadPool.execute(() -> {
                LOGGER.info("----task3");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            threadPool.execute(() -> {
                LOGGER.info("----task4");
                try {
                    Thread.sleep(12000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            threadPool.execute(() -> {
                LOGGER.info("----task5");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            threadPool.shutdown();
        }

    }
}
