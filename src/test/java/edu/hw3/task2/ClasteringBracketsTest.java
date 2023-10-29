package edu.hw3.task2;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class ClasteringBracketsTest {
    @ParameterizedTest
    @DisplayName("Тест на корректную работу")
    @MethodSource("provideNormalCase")
    void testClusterizeNormalCase(String input, List<String> expected) {
        ClusteringBrackets worker = new ClusteringBrackets();

        List<String> actual = worker.clusterize(input);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideNormalCase() {
        return Stream.of(
            Arguments.of("()()()", List.of("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())", List.of("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", List.of("((())())", "(()(()()))"))
        );
    }

    @ParameterizedTest
    @DisplayName("Тест на исключение из-за некорректного ввода")
    @MethodSource("provideInvalidInputCase")
    void testClusterizeInvalidInputCase(String input) {
        ClusteringBrackets worker = new ClusteringBrackets();

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> worker.clusterize(input));
    }

    private static Stream<Arguments> provideInvalidInputCase() {
        return Stream.of(
            Arguments.of((Object) null),
            Arguments.of(""),
            Arguments.of("    "),
            Arguments.of("(a)(b)(c)"),
            Arguments.of("())(()))"),
            Arguments.of("((()))(())()((()(()()(")
        );
    }
}
