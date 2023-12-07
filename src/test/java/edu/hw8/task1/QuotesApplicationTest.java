package edu.hw8.task1;

import edu.hw8.task1.client.Client;
import edu.hw8.task1.client.QuotesClient;
import edu.hw8.task1.server.QuotesServer;
import edu.hw8.task1.server.Server;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

final class QuotesApplicationTest {
    @ParameterizedTest
    @DisplayName("Тестирование работы QuotesServer и одним QuotesClient")
    @MethodSource("provideRequestsAndResponses")
    void checkResponses(List<String> requests, List<String> expected) throws InterruptedException {
        List<String> actualResponses = new ArrayList<>();
        Executor threadPool = Executors.newFixedThreadPool(2);

        Server server = QuotesServer.of(11111);
        threadPool.execute(server::start);
        Thread.sleep(1000);
        threadPool.execute(
            () -> {
                Client client = QuotesClient.of("John", 11111);
                for (String request : requests) {
                    actualResponses.add(client.request(request));
                }
            }
        );
        Thread.sleep(3000);
        server.stop();

        assertThat(actualResponses).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Тестирование работы QuotesServer и несколькими QuotesClient")
    @MethodSource("provideRequestsAndResponses")
    void checkResponsesWithMultiConnection(List<String> requests, List<String> expected) throws InterruptedException {
        List<String> actualResponses = new CopyOnWriteArrayList<>();
        Executor threadPool = Executors.newFixedThreadPool(5);

        Server server = QuotesServer.of(11112);
        threadPool.execute(server::start);
        Thread.sleep(1000);

        for (String request : requests) {
            threadPool.execute(
                () -> {
                    Client client = QuotesClient.of("John", 11112);
                    actualResponses.add(client.request(request));
                }
            );
        }
        Thread.sleep(3000);
        server.stop();

        assertThat(actualResponses).containsExactlyInAnyOrderElementsOf(expected);
    }

    private static Stream<Arguments> provideRequestsAndResponses() {
        return Stream.of(
            Arguments.of(
                List.of(
                    "личности",
                    "оскорбления",
                    "глупый",
                    "интеллект"
                ),
                List.of(
                    "Не переходи на личности там, где их нет.",
                    "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.",
                    "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
                    "Чем ниже интеллект, тем громче оскорбления."
                )
            )
        );
    }
}
