package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FixedThreadPool implements ThreadPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final long DEFAULT_SECONDS_TIMEOUT_FOR_TASK = 3;
    private final BlockingQueue<Runnable> tasks;
    private final Worker[] workers;
    private final AtomicBoolean isRunning;

    private FixedThreadPool(int poolSize) {
        tasks = new LinkedBlockingDeque<>(poolSize);
        workers = new Worker[poolSize];
        for (int i = 0; i < poolSize; i++) {
            workers[i] = new Worker(DEFAULT_SECONDS_TIMEOUT_FOR_TASK);
        }
        isRunning = new AtomicBoolean(true);
    }

    private FixedThreadPool(int poolSize, long secondsOfWaitingForTask) {
        tasks = new LinkedBlockingDeque<>(poolSize);
        workers = new Worker[poolSize];
        for (int i = 0; i < poolSize; i++) {
            workers[i] = new Worker(secondsOfWaitingForTask);
        }
        isRunning = new AtomicBoolean(true);
    }

    public static FixedThreadPool of(int poolSize) {
        validatePoolSize(poolSize);
        return new FixedThreadPool(poolSize);
    }

    public static FixedThreadPool of(int poolSize, int secondsOfWaitingForTask) {
        validatePoolSize(poolSize);
        validateTimeoutValue(secondsOfWaitingForTask);
        return new FixedThreadPool(poolSize);
    }

    @Override
    public void start() {
        for (Worker worker : workers) {
            worker.start();
        }
    }

    @Override
    public void execute(Runnable task) {
        try {
            LOGGER.info(LogMessages.ADDING_TASK, Thread.currentThread().threadId());
            tasks.put(task);
            LOGGER.info(LogMessages.ADDED_TASK, Thread.currentThread().threadId());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.info(LogMessages.INTERRUPTED, Thread.currentThread().threadId(), e.getMessage());
        }
    }

    public void shutdown() {
        isRunning.set(false);
    }

    @Override
    public void close() {
        for (Worker worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.info(LogMessages.INTERRUPTED, Thread.currentThread().threadId(), e.getMessage());
            }
        }
    }

    private static void validatePoolSize(int poolSize) {
        if (poolSize < 1) {
            throw new IllegalArgumentException("Number of threads must be positive!");
        }
    }

    private static void validateTimeoutValue(int secondsTimeout) {
        if (secondsTimeout < 1) {
            throw new IllegalArgumentException("Number of seconds must be positive!");
        }
    }

    public final class Worker extends Thread {
        private final long secondsOfWaitingForTask;

        public Worker(long secondsOfWaitingForTask) {
            this.secondsOfWaitingForTask = secondsOfWaitingForTask;
        }

        @Override
        public void run() {
            while (isRunning.get() || !tasks.isEmpty()) {
                try {
                    LOGGER.info(LogMessages.GETTING_TASK, Thread.currentThread().threadId());
                    Runnable task = tasks.poll(secondsOfWaitingForTask, TimeUnit.SECONDS);

                    if (task == null) {
                        LOGGER.info(LogMessages.NO_TASK_AVAILABLE, Thread.currentThread().threadId());
                        continue;
                    }
                    LOGGER.info(LogMessages.HAS_TASK, Thread.currentThread().threadId());
                    task.run();
                    LOGGER.info(LogMessages.EXECUTED_TASK, Thread.currentThread().threadId());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    LOGGER.info(LogMessages.INTERRUPTED, Thread.currentThread().threadId(), e.getMessage());
                }
            }
            LOGGER.info(LogMessages.FINISHED, Thread.currentThread().threadId());
        }
    }
}
