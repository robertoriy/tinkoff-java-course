package edu.hw2.task3.manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

final class DefaultConnectionManagerTest {
    @ParameterizedTest
    @DisplayName("Тест на получение неисправного соединения с заданной вероятностью")
    @CsvSource({
        "1, 100, 100",
        "0, 100, 0"
    })
    void testGetConnection(double faultyConnectionProbability, int maxAttempts, int expected) {
        ConnectionManager connectionManager = new DefaultConnectionManager(faultyConnectionProbability);
        int actualNumber = 0;

        for (int i = 1; i <= maxAttempts; i++) {
            Connection connection = connectionManager.getConnection();
            if (connection instanceof FaultyConnection) {
                actualNumber++;
            }
            connection.close();
        }

        assertThat(actualNumber).isEqualTo(expected);
    }
}
