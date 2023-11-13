package edu.hw5.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class BinaryAlphabetUtilsTest {
    @Nested
    class Task1 {
        @ParameterizedTest
        @DisplayName("Тест задача 1. Для строк соответствующих правилу")
        @CsvSource({
            "110",
            "000",
            "110111",
            "000000",
            "01010",
            "100010"
        })
        void validationTrueCase(String input) {
            boolean actual = BinaryAlphabetUtils.containAtLeastThreeSymbolsAndThirdIsZero(input);

            assertTrue(actual);
        }

        @ParameterizedTest
        @DisplayName("Тест задача 1. Для строк не соответствующих правилу")
        @CsvSource({
            "111",
            "001",
            "111111",
            "001000",
            "01110",
            "101010",
            "123124",
            "1201231",
            "as0d"
        })
        void validationFalseCase(String input) {
            boolean actual = BinaryAlphabetUtils.containAtLeastThreeSymbolsAndThirdIsZero(input);

            assertFalse(actual);
        }
    }

    @Nested
    class Task2 {
        @ParameterizedTest
        @DisplayName("Тест задача 2. Для строк соответствующих правилу")
        @CsvSource({
            "1",
            "0",
            "11",
            "00",
            "101",
            "111",
            "000",
            "010",
            "010101001010",
            "10101001011"
        })
        void validationTrueCase(String input) {
            boolean actual = BinaryAlphabetUtils.startsAndEndsWithTheSameSymbol(input);

            assertTrue(actual);
        }

        @ParameterizedTest
        @DisplayName("Тест задача 2. Для строк не соответствующих правилу")
        @CsvSource({
            "10",
            "01",
            "100",
            "011",
            "100",
            "011",
            "10101001010",
            "010101001011"
        })
        void validationFalseCase(String input) {
            boolean actual = BinaryAlphabetUtils.startsAndEndsWithTheSameSymbol(input);

            assertFalse(actual);
        }
    }

    @Nested
    class Task3 {
        @ParameterizedTest
        @DisplayName("Тест задача 3. Для строк соответствующих правилу")
        @CsvSource({
            "1",
            "0",
            "00",
            "01",
            "10",
            "11",
            "110",
            "111",
            "000",
            "001",
            "100",
            "101",
            "010",
            "011",
        })
        void validationTrueCase(String input) {
            boolean actual = BinaryAlphabetUtils.hasLengthNotMoreThanThree(input);

            assertTrue(actual);
        }

        @Test
        @DisplayName("Тест задача 3. Для строк не соответствующих правилу - пустая строка")
        void validationEmptyInputFalseCase() {
            boolean actual = BinaryAlphabetUtils.hasLengthNotMoreThanThree("");

            assertFalse(actual);
        }

        @ParameterizedTest
        @DisplayName("Тест задача 3. Для строк не соответствующих правилу - длинные строки")
        @CsvSource({
            "1010",
            "0000",
            "10101",
            "110010110"
        })
        void validationLongFalseCase(String input) {
            boolean actual = BinaryAlphabetUtils.hasLengthNotMoreThanThree(input);

            assertFalse(actual);
        }
    }

    @Test
    @DisplayName("Тест для некорректных входных данных")
    void badInputCase() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> BinaryAlphabetUtils.containAtLeastThreeSymbolsAndThirdIsZero(null));

        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> BinaryAlphabetUtils.startsAndEndsWithTheSameSymbol(null));

        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> BinaryAlphabetUtils.hasLengthNotMoreThanThree(null));
    }
}
