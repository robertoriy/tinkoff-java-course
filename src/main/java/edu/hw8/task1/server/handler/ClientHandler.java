package edu.hw8.task1.server.handler;

import edu.hw8.task1.Messages;
import edu.hw8.task1.QuoteDictionary;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.net.Socket;

public final class ClientHandler implements Handler {
    private final Socket client;

    private ClientHandler(Socket client) {
        this.client = client;
    }

    public static ClientHandler create(Socket client) {
        return new ClientHandler(client);
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    @Override
    public void handle() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter writer = new PrintWriter(client.getOutputStream(), true)) {

            String name = reader.readLine();
            String topic = reader.readLine();
            System.out.print(Messages.USER.formatted(name, topic));

            String response = QuoteDictionary.getQuote(topic);
            writer.println(response);
            System.out.print(Messages.SERVER.formatted(response));

        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }

    @Override
    public void close() {
        try {
            client.close();
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }
}
