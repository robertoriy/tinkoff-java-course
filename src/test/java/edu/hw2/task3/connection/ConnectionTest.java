package edu.hw2.task3.connection;

import edu.hw2.task3.exception.ConnectionException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class ConnectionTest {
    @Nested
    class StableConnectionTest {
        @ParameterizedTest
        @DisplayName("Тест исправного соединения: многократное выполнение команд без ошибок")
        @ValueSource(ints = {10, 30, 100, 999})
        void testExecute(int maxAttempts) {
            Connection connection = new StableConnection();

            assertThatCode(() -> {
                for (int i = 0; i < maxAttempts; i++) {
                    connection.execute("git reset --hard HEAD~2");
                }
            }).doesNotThrowAnyException();
            connection.close();
        }
    }

    @Nested
    class FaultyConnectionTest {
        @ParameterizedTest
        @DisplayName("Тест неисправного соединения: 2 из 3 раз бросает исключение")
        @CsvSource({
            "30, 20",
            "6, 4",
            "99, 66"
        })
        void testExecute(int maxAttempts, int expectedNumberOfExceptions) {
            int actualNumberOfExceptions = 0;
            Connection connection = new FaultyConnection();

            for (int i = 0; i < maxAttempts; i++) {
                try {
                    connection.execute("git reset --hard HEAD~2");
                } catch (ConnectionException e) {
                    actualNumberOfExceptions++;
                }
            }
            connection.close();

            assertThat(actualNumberOfExceptions).isEqualTo(expectedNumberOfExceptions);
        }
    }

    @Nested
    class ClosingConnection {
        @Test
        @DisplayName("Тест на закрытие соединения")
        void testClose() {
            List<Connection> connectionPull = new ArrayList<Connection>();

            try (Connection stableConnection = new StableConnection();
                 Connection faultyConnection = new FaultyConnection()) {
                connectionPull.add(stableConnection);
                connectionPull.add(faultyConnection);
            } catch (Exception e) {
                throw new RuntimeException("Something went wrong", e);
            }

            for (Connection connection : connectionPull) {
                assertFalse(connection.isOpen());
            }
        }
    }
}
