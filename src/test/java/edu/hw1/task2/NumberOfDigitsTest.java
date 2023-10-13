package edu.hw1.task2;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.DisplayName;

final class NumberOfDigitsTest {
    @ParameterizedTest
    @DisplayName("Тест для обычных значений")
    @CsvSource({
        "1, 1",
        "-52, 2",
        "123, 3",
        "-4567, 4",
        "264841, 6"
    })
    void testCountDigitsNormalCase(int number, int expected) {
        int actual = NumberOfDigits.countDigits(number);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Тест для краевых значений")
    @CsvSource({
        "0, 1",
        "2147483647, 10",
        "-2147483648, 10"
    })
    void testCountDigitsEdgeCase(int number, int expected) {
        int actual = NumberOfDigits.countDigits(number);

        assertThat(actual).isEqualTo(expected);
    }
}
