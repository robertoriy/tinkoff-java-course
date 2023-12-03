package edu.hw8.task1.server;

import edu.hw8.task1.server.handler.ClientHandler;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class QuotesServer implements Server {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int DEFAULT_PORT = 11111;
    private static final int DEFAULT_MAX_CONNECTIONS = 3;
    private final ExecutorService threadPool;
    private final int port;
    private boolean isRunning;

    private QuotesServer(int port, int maxAvailableConnections) {
        this.port = port;
        threadPool = Executors.newFixedThreadPool(maxAvailableConnections);
        isRunning = true;
    }

    public static QuotesServer of(int port, int maxAvailableConnections) {
        return new QuotesServer(port, maxAvailableConnections);
    }

    public static QuotesServer of(int port) {
        return new QuotesServer(port, DEFAULT_MAX_CONNECTIONS);
    }

    public static QuotesServer defaultInstance() {
        return new QuotesServer(DEFAULT_PORT, DEFAULT_MAX_CONNECTIONS);
    }

    @Override
    public void start() {
        LOGGER.info("Server is running on port - {}", port);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (isRunning) {
                Socket client = serverSocket.accept();
                threadPool.execute(
                    () -> {
                        try (ClientHandler handler = ClientHandler.create(client)) {
                            handler.handle();
                        }
                    }
                );
            }
            LOGGER.info("Server finished the job");
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }

    @Override
    public void stop() {
        isRunning = false;
    }
}
