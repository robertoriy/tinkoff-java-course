package edu.hw5.task2;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class FridayTheThirteenthTest {
    @ParameterizedTest
    @DisplayName("Тест на поиск всех пятниц 13 в заданном году")
    @MethodSource("provideAllFridaysNormalCase")
    void getAllFridays13thInYearNormalCase(int year, List<LocalDate> expected) {
        List<LocalDate> actual = FridayTheThirteenth.findAllInYear(year);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAllFridaysNormalCase() {
        return Stream.of(
            Arguments.of(
                1925,
                List.of(
                    LocalDate.of(1925, Month.FEBRUARY, 13),
                    LocalDate.of(1925, Month.MARCH, 13),
                    LocalDate.of(1925, Month.NOVEMBER, 13)
                )
            ),
            Arguments.of(
                2024,
                List.of(
                    LocalDate.of(2024, Month.SEPTEMBER, 13),
                    LocalDate.of(2024, Month.DECEMBER, 13)
                )
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест на поиск следующей пятницы 13")
    @MethodSource("provideNextFridayNormalCase")
    void getNextFriday13thNormalCase(LocalDate current, LocalDate expected) {
        LocalDate actual = FridayTheThirteenth.getNext(current);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideNextFridayNormalCase() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(2023, Month.NOVEMBER, 14),
                LocalDate.of(2024, Month.SEPTEMBER, 13)
            ),
            Arguments.of(
                LocalDate.of(2024, Month.SEPTEMBER, 14),
                LocalDate.of(2024, Month.DECEMBER, 13)
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест некорректных входных данных")
    @ValueSource(ints = {-124, -1, 0})
    void getAllFridays13thInYearBadInputCase(int year) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> FridayTheThirteenth.findAllInYear(year));
    }
}
