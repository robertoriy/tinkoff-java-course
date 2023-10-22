package edu.hw2.task3;

import edu.hw2.task3.exception.ConnectionException;
import edu.hw2.task3.manager.FaultyConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

final class PopularCommandExecutorTest {
    @Test
    @DisplayName("Тест на получение исключения с причиной при неудаче выполнения команды")
    void testTryExecute() {
        PopularCommandExecutor executor = new PopularCommandExecutor(new FaultyConnectionManager(), 10);

        assertThatThrownBy(() -> executor.tryExecute("git reset --hard HEAD~2"))
            .isInstanceOf(ConnectionException.class)
            .hasMessage("Limit of command execution attempts exceeded")
            .hasCauseInstanceOf(ConnectionException.class)
            .hasRootCauseMessage("Failed to execute the command - git reset --hard HEAD~2");
    }
}
