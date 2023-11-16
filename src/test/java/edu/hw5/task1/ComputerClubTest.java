package edu.hw5.task1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class ComputerClubTest {
    @ParameterizedTest
    @DisplayName("Тест для расчета средний длительности посещений")
    @MethodSource("provideNormalCase")
    void averageDurationNormalCase(List<String> timeRanges, String expected) {
        String actual = ComputerClub.getAverageTime(timeRanges);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideNormalCase() {
        return Stream.of(
            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                ),
                "3ч 40м"
            ),
            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 23:50"
                ),
                "3ч 30м"
            ),
            Arguments.of(
                List.of(
                    "2022-03-12, 09:00 - 2022-03-12, 12:00",
                    "2022-03-12, 14:30 - 2022-03-12, 17:45",
                    "2022-03-12, 19:30 - 2022-03-12, 22:15"
                ),
                "3ч 0м"
            ),
            Arguments.of(
                List.of(
                    "2022-03-12, 08:00 - 2022-03-12, 10:45",
                    "2022-03-12, 12:30 - 2022-03-12, 14:20",
                    "2022-03-12, 18:10 - 2022-03-12, 21:30"
                ),
                "2ч 38м"
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест некорректных входных данных")
    @MethodSource("provideBadInputCase")
    void averageDurationBadInputCase(List<String> timeRanges) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> ComputerClub.getAverageTime(timeRanges));
    }

    private static Stream<Arguments> provideBadInputCase() {
        return Stream.of(
            Arguments.of((Object) null),
            Arguments.of(Collections.emptyList()),
            Arguments.of(Arrays.asList("Hello", null, "World")),
            Arguments.of(List.of("Hello", "World"))
        );
    }
}
