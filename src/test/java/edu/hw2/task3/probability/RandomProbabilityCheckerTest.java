package edu.hw2.task3.probability;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

final class RandomProbabilityCheckerTest {
    @ParameterizedTest
    @DisplayName("Тест на наступление события с заданной вероятностью")
    @MethodSource("provideProbabilities")
    void testHasEventOccurred(double probabilityOfEvent, boolean expected) {
        ProbabilityChecker random = new RandomProbabilityChecker(probabilityOfEvent);

        boolean actual = random.hasEventOccurred();

        assertThat(actual).isEqualTo(expected);
    }

    public static Stream<Arguments> provideProbabilities() {
        return Stream.of(
            Arguments.of(1, true),
            Arguments.of(0, false)
        );
    }
}
