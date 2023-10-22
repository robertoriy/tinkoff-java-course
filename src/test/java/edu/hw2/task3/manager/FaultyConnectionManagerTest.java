package edu.hw2.task3.manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

final class FaultyConnectionManagerTest {
    @ParameterizedTest
    @DisplayName("Тест на получение неисправного соединения")
    @ValueSource(ints = {3, 5, 7, 12, 55, 546})
    void testGetConnection(int maxAttempts) {
        ConnectionManager connectionManager = new FaultyConnectionManager();

        for (int i = 1; i <= maxAttempts; i++) {
            Connection connection = connectionManager.getConnection();
            assertThat(connection).isInstanceOf(FaultyConnection.class);
            connection.close();
        }
    }
}
