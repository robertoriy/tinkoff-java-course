package edu.hw8.task2;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

final class FibonacciApplicationTest {
    @ParameterizedTest
    @DisplayName("Тестирование работы FixedThreadPool с числами Фибоначчи")
    @MethodSource("provideFibonacciData")
    void checkFibonacciComputing(int poolSize, List<Integer> values, List<Integer> expected)
        throws InterruptedException {
        List<Integer> actual = FibonacciApplication.compute(poolSize, values);
        Thread.sleep(3000);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideFibonacciData() {
        return Stream.of(
            Arguments.of(
                3,
                List.of(5, 10, 8, 7, 9, 6),
                List.of(5, 55, 21, 13, 34, 8)
            )
        );
    }
}
