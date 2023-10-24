package edu.hw2.task3;

import edu.hw2.task3.exception.ConnectionException;
import edu.hw2.task3.exception.LimitOfExecutionAttemptsExceeded;
import edu.hw2.task3.manager.DefaultConnectionManager;
import edu.hw2.task3.manager.FaultyConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

final class PopularCommandExecutorTest {
    @Test
    @DisplayName("Тест на успешное выполнение команды")
    void testTryExecuteNormalCase() {
        String command = "git reset --hard HEAD~2";
        var executor = new PopularCommandExecutor(new DefaultConnectionManager(0), 10);

        assertThatCode(() -> executor.tryExecute(command)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Тест на получение исключения при достижении лимита попыток выполнения")
    void testTryExecuteLimitExceedCase() {
        String command = "git reset --hard HEAD~2";
        var executor = new PopularCommandExecutor(new FaultyConnectionManager(1), 10);

        assertThatThrownBy(() -> executor.tryExecute(command))
            .isInstanceOf(LimitOfExecutionAttemptsExceeded.class)
            .hasMessage("Limit of command execution attempts exceeded")
            .hasCauseInstanceOf(ConnectionException.class)
            .hasRootCauseMessage("Failed to execute the command - " + command);
    }
}
