package edu.hw3.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class RomanNumeralsTest {
    @ParameterizedTest
    @DisplayName("Тест на корректную работу")
    @CsvSource({
        "10, X",
        "2, II",
        "12, XII",
        "16, XVI",
        "592, DXCII",
        "1334, MCCCXXXIV",
        "2736, MMDCCXXXVI",
        "3999, MMMCMXCIX"
    })
    void testConvertToRomanNormalCase(int input, String expected) {
        String actual = RomanNumerals.convertToRoman(input);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Тест на исключение при некорректном вводе")
    @ValueSource(ints = {-100, -1, 0, 4000, 5000, 123213})
    void testConvertToRomanInvalidInputCase(int input) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> RomanNumerals.convertToRoman(input));
    }
}
