package edu.hw2.task3.manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

final class DefaultConnectionManagerTest {
    @ParameterizedTest
    @DisplayName("Тест на получение неисправного соединения на каждый четвертый запрос")
    @ValueSource(ints = {3, 5, 7, 12, 55, 546})
    void testGetConnection(int maxAttempts) {
        ConnectionManager connectionManager = new DefaultConnectionManager();

        for (int i = 1; i <= maxAttempts; i++) {
            Connection connection = connectionManager.getConnection();
            if (i % 4 == 0) {
                assertThat(connection).isInstanceOf(FaultyConnection.class);
            } else {
                assertThat(connection).isInstanceOf(StableConnection.class);
            }
            connection.close();
        }
    }
}
