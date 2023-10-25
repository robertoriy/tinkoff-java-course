package edu.hw2.task3.retry;

import edu.hw2.task3.exception.LimitOfExecutionAttemptsExceededException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

final class LimitedRetryTest {
    @ParameterizedTest
    @DisplayName("Тест на проверку одиночного успешного выполнения команды")
    @CsvSource({
        "5, 1",
        "5, 1"
    })
    void testExecuteNormalCase(int maxAttempts, int expected) {
        Retry retry = new LimitedRetry.LimitedRetryBuilder().maxAttempts(maxAttempts).build();
        List<Integer> actualList = new ArrayList<>();

        retry.execute(() -> {
            actualList.add(1);
        });

        assertThat(actualList).hasSize(expected);
    }

    @ParameterizedTest
    @DisplayName("Тест на проверку повторного выполнения команды заданное количество раз при неудаче")
    @CsvSource({
        "5, 5",
        "20, 20"
    })
    void testExecuteRetryCase(int maxAttempts, Integer expected) {
        Retry retry = new LimitedRetry.LimitedRetryBuilder().maxAttempts(maxAttempts).build();
        List<Integer> actualList = new ArrayList<>();
        Exception actualException = null;

        try {
            retry.execute(() -> {
                actualList.add(1);
                throw new RuntimeException();
            });
        } catch (Exception e) {
            actualException = e;
        }

        assertThat(actualList).hasSize(expected);
        assertThat(actualException).isInstanceOf(LimitOfExecutionAttemptsExceededException.class);
    }
}
