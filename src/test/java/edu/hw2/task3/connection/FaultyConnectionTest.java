package edu.hw2.task3.connection;

import edu.hw2.task3.exception.ConnectionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class FaultyConnectionTest {
    @ParameterizedTest
    @DisplayName("Тест на получение исключения с заданной вероятностью")
    @CsvSource({
        "1, 100, 100",
        "0, 100, 0"
    })
    void testExecute(double failureProbability, int maxAttempts, int expected) {
        Connection connection = new FaultyConnection(failureProbability);
        int actualNumber = 0;

        for (int i = 0; i < maxAttempts; i++) {
            try {
                connection.execute("git reset --hard HEAD~2");
            } catch (ConnectionException e) {
                actualNumber++;
            }
        }
        connection.close();

        assertThat(actualNumber).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест на закрытие соединения")
    void testClose() {
        Connection connection;

        try (Connection faultyConnection = new FaultyConnection(1)) {
            connection = faultyConnection;
            assertTrue(connection.isOpen());
        }
        assertFalse(connection.isOpen());
    }
}
