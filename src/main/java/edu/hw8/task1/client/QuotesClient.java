package edu.hw8.task1.client;

import edu.hw8.task1.Messages;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.net.InetAddress;
import java.net.Socket;

public final class QuotesClient implements Client {
    private static final int DEFAULT_SERVER_PORT = 11111;
    private static final String HOST_NAME = "localhost";
    private final String name;
    private final int port;

    private QuotesClient(String name, int port) {
        this.name = name;
        this.port = port;
    }

    public static QuotesClient of(String name, int port) {
        return new QuotesClient(name, port);
    }

    public static QuotesClient of(String name) {
        return new QuotesClient(name, DEFAULT_SERVER_PORT);
    }

    @Override
    public String request(String topic) {
        try (Socket socket = new Socket(InetAddress.getByName(HOST_NAME), port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            writer.println(name);
            writer.println(topic);
            print(Messages.USER.formatted(name, topic));

            String response = reader.readLine();
            print(Messages.SERVER.formatted(response));

            return response;
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    private void print(String line) {
        System.out.print(line);
    }
}
