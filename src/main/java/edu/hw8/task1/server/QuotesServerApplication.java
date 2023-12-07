package edu.hw8.task1.server;

public final class QuotesServerApplication {
    private QuotesServerApplication() {
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static void main(String[] args) throws InterruptedException {
        QuotesServer server = QuotesServer.defaultInstance();
        server.start();

        Thread.sleep(10000);
        server.stop();
    }
}
