package edu.hw5.task3;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class CombinedDateParserTest {
    @ParameterizedTest
    @DisplayName("Тест для строк соответствующих правилу дат")
    @MethodSource("provideParsedCase")
    void parseDateParsedCase(String date, Optional<LocalDate> expected) {
        Optional<LocalDate> actual = CombinedDateParser.parseDate(date);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideParsedCase() {
        return Stream.of(
            Arguments.of(
                "2020-10-10",
                Optional.of(LocalDate.of(2020, Month.OCTOBER, 10))
            ),
            Arguments.of(
                "2020-12-2",
                Optional.of(LocalDate.of(2020, Month.DECEMBER, 2))
            ),
            Arguments.of(
                "1/3/1976",
                Optional.of(LocalDate.of(1976, Month.MARCH, 1))
            ),
            Arguments.of(
                "1/3/20",
                Optional.of(LocalDate.of(2020, Month.MARCH, 1))
            ),
            Arguments.of(
                "tomorrow",
                Optional.of(LocalDate.now().plusDays(1))
            ),
            Arguments.of(
                "today",
                Optional.of(LocalDate.now())
            ),
            Arguments.of(
                "yesterday",
                Optional.of(LocalDate.now().minusDays(1))
            ),
            Arguments.of(
                "1 day ago",
                Optional.of(LocalDate.now().minusDays(1))
            ),
            Arguments.of(
                "2234 days ago",
                Optional.of(LocalDate.now().minusDays(2234))
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест для строк несоответствующих правилу")
    @CsvSource({
        "2020-180-20",
        "15:10:2013",
        "155/1f/20213",
        "А123ВЕ7777",
        "fsdgsdfg"
    })
    void parseDateEmptyCase(String input) {
        Optional<LocalDate> actual = CombinedDateParser.parseDate(input);

        assertThat(actual).isNotPresent();
    }

    @Test
    @DisplayName("Тест для некорректных входных данных")
    void parseDateBadInputCase() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> CombinedDateParser.parseDate(null));
    }
}
