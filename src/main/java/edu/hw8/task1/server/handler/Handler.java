package edu.hw8.task1.server.handler;

public interface Handler extends AutoCloseable {
    void handle();

    void close();
}
