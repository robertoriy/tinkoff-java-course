package edu.hw5.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class ExtendedBinaryAlphabetUtilsTest {
    @Nested
    class Task1 {
        @ParameterizedTest
        @DisplayName("Тест задача 1. Для строк соответствующих правилу")
        @CsvSource({
            "1",
            "0",
            "111",
            "100",
            "01011",
            "0101101"
        })
        void validationTrueCase(String input) {
            boolean actual = ExtendedBinaryAlphabetUtils.hasOddLength(input);

            assertTrue(actual);
        }

        @ParameterizedTest
        @DisplayName("Тест задача 1. Для строк не соответствующих правилу")
        @CsvSource({
            "11",
            "01",
            "1101",
            "1001",
            "010101",
            "01011101"
        })
        void validationFalseCase(String input) {
            boolean actual = ExtendedBinaryAlphabetUtils.hasOddLength(input);

            assertFalse(actual);
        }
    }

    @Nested
    class Task2 {
        @ParameterizedTest
        @DisplayName("Тест задача 2. Для строк соответствующих правилу")
        @CsvSource({
            "011",
            "010",
            "01011",
            "0000101",
            "11",
            "10",
            "1011",
            "100101"
        })
        void validationTrueCase(String input) {
            boolean actual = ExtendedBinaryAlphabetUtils.startsZeroAndOddOrStartsOneAndEven(input);

            assertTrue(actual);
        }

        @ParameterizedTest
        @DisplayName("Тест задача 2. Для строк не соответствующих правилу")
        @CsvSource({
            "01",
            "00",
            "0111",
            "000101",
            "1",
            "101",
            "10110",
            "1001011"
        })
        void validationFalseCase(String input) {
            boolean actual = ExtendedBinaryAlphabetUtils.startsZeroAndOddOrStartsOneAndEven(input);

            assertFalse(actual);
        }
    }

    @Nested
    class Task3 {
        @ParameterizedTest
        @DisplayName("Тест задача 3. Для строк соответствующих правилу")
        @CsvSource({
            "1111",
            "000",
            "10010",
            "01010",
            "101010",
            "100101110100"

        })
        void validationTrueCase(String input) {
            boolean actual = ExtendedBinaryAlphabetUtils.numberOfZerosIsMultipleOfThree(input);

            assertTrue(actual);
        }

        @ParameterizedTest
        @DisplayName("Тест задача 3. Для строк не соответствующих правилу")
        @CsvSource({
            "11101",
            "00",
            "10000",
            "010",
            "1010100",
            "1001011010100"
        })
        void validationFalseCase(String input) {
            boolean actual = ExtendedBinaryAlphabetUtils.numberOfZerosIsMultipleOfThree(input);

            assertFalse(actual);
        }
    }

    @Nested
    class Task4 {
        @ParameterizedTest
        @DisplayName("Тест задача 4. Для строк соответствующих правилу")
        @CsvSource({
            "1",
            "10",
            "11",
            "111",
            "101",
            "1110",
            "1011",
            "10101010101010"
        })
        void validationTrueCase(String input) {
            boolean actual = ExtendedBinaryAlphabetUtils.isEveryOddSymbolOne(input);

            assertTrue(actual);
        }

        @ParameterizedTest
        @DisplayName("Тест задача 4. Для строк не соответствующих правилу")
        @CsvSource({
            "0",
            "00",
            "01",
            "110",
            "001",
            "1100",
            "0011",
            "10001010001010"
        })
        void validationFalseCase(String input) {
            boolean actual = ExtendedBinaryAlphabetUtils.isEveryOddSymbolOne(input);

            assertFalse(actual);
        }
    }

    @Test
    @DisplayName("Тест для некорректных входных данных")
    void badInputCase() {
        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> ExtendedBinaryAlphabetUtils.hasOddLength(null));

        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> ExtendedBinaryAlphabetUtils.startsZeroAndOddOrStartsOneAndEven(null));

        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> ExtendedBinaryAlphabetUtils.numberOfZerosIsMultipleOfThree(null));

        assertThatExceptionOfType(NullPointerException.class)
            .isThrownBy(() -> ExtendedBinaryAlphabetUtils.isEveryOddSymbolOne(null));
    }
}
