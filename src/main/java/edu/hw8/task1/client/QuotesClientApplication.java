package edu.hw8.task1.client;

public final class QuotesClientApplication {
    private QuotesClientApplication() {
    }

    @SuppressWarnings({"checkstyle:RegexpSinglelineJava", "checkstyle:MagicNumber"})
    public static void main(String[] args) throws InterruptedException {
        QuotesClient client = QuotesClient.of("John");
        String response1 = client.request("личности");
        String response2 = client.request("интеллект");

        System.out.println(response1);
        System.out.println(response2);
    }
}
