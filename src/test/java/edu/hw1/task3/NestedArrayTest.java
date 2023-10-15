package edu.hw1.task3;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class NestedArrayTest {
    @ParameterizedTest
    @DisplayName("Тест для вложенных массивов")
    @MethodSource("provideTestCasesForNestableScenarios")
    void testIsNestableTrueCase(int[] first, int[] second) {
        boolean actual = NestedArray.isNestable(first, second);

        assertThat(actual).isTrue();
    }

    private static Stream<Arguments> provideTestCasesForNestableScenarios() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {0, 6}),
            Arguments.of(new int[] {3, 1}, new int[] {4, 0}),
            Arguments.of(new int[] {4, 5, 25}, new int[] {3, 20, 26})
        );
    }

    @ParameterizedTest
    @DisplayName("Тест для невложенных массивов")
    @MethodSource("provideTestCasesForNotNestableScenarios")
    void testIsNestableFalseCase(int[] first, int[] second) {
        boolean actual = NestedArray.isNestable(first, second);

        assertThat(actual).isFalse();
    }

    private static Stream<Arguments> provideTestCasesForNotNestableScenarios() {
        return Stream.of(
            Arguments.of(new int[] {4, 6}, new int[] {1, 2, 3, 4}),
            Arguments.of(new int[] {9, 9, 8}, new int[] {8, 9}),
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {2, 3}),
            Arguments.of(new int[] {3, 20, 26}, new int[] {4, 5, 25})
        );
    }

    @ParameterizedTest
    @DisplayName("Тест для некорректных входных данных")
    @MethodSource("provideTestCasesForInvalidInput")
    void testIsNestableInvalidInputCase(int[] first, int[] second) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> NestedArray.isNestable(first, second));
    }

    private static Stream<Arguments> provideTestCasesForInvalidInput() {
        return Stream.of(
            Arguments.of(new int[] {}, new int[] {}),
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {}),
            Arguments.of(new int[] {}, new int[] {3, 20, 26}),
            Arguments.of(null, null),
            Arguments.of(null, new int[] {4, 0}),
            Arguments.of(new int[] {1, 2, 3, 4}, null)
        );
    }
}
