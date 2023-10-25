package edu.hw2.task3.connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class StableConnectionTest {
    @ParameterizedTest
    @DisplayName("Тест на многократное выполнение без исключений")
    @ValueSource(ints = {100})
    void testExecute(int maxAttempts) {
        Connection connection = new StableConnection();

        assertThatCode(() -> {
            for (int i = 0; i < maxAttempts; i++) {
                connection.execute("git reset --hard HEAD~2");
            }
        }).doesNotThrowAnyException();
        connection.close();
    }

    @Test
    @DisplayName("Тест на закрытие соединения")
    void testClose() {
        Connection connection = null;

        try (Connection stableConnection = new StableConnection()) {
            connection = stableConnection;
            assertTrue(connection.isOpen());
        }
        assertFalse(connection.isOpen());
    }
}
