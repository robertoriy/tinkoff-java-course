package edu.hw3.task3;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class FrequencyTest {
    @ParameterizedTest
    @DisplayName("Тест на корректную частоту слов")
    @MethodSource("provideNormalCase")
    <T> void testComputeNormalCase(List<T> input, Map<T, Integer> expected) {
        Map<T, Integer> frequency = Frequency.compute(input);

        assertThat(frequency).isEqualTo(expected);
    }

    private static Stream<Arguments> provideNormalCase() {
        return Stream.of(
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("bb", 2, "a", 2)),
            Arguments.of(List.of("this", "and", "that", "and"), Map.of("that", 1, "and", 2, "this", 1)),
            Arguments.of(List.of("код", "код", "код", "bug"), Map.of("код", 3, "bug", 1)),
            Arguments.of(List.of(1, 1, 2, 2), Map.of(1, 2, 2, 2))
        );
    }

    @Test
    @DisplayName("Тест на исключение из-за некорректного ввода")
    void testComputeInvalidInputCase() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Frequency.compute(null));
    }
}
