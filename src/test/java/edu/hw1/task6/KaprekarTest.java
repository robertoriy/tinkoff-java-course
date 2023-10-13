package edu.hw1.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class KaprekarTest {
    @ParameterizedTest
    @DisplayName("Тест для обычных значений")
    @CsvSource({
        "6174, 0",
        "3285, 1",
        "3524, 3",
        "6621, 5",
        "6554, 4",
        "1234, 3",
        "8991, 3"
    })
    void testCountKNormalCase(int number, int expected) {
        int actual = Kaprekar.countK(number);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Тест для чисел с одинаковыми цифрами")
    @ValueSource(ints = {1111, 2222, 3333, 4444, 5555, 6666, 7777, 8888, 9999})
    void testCountKRepdigitCase(int number) {
        assertThrows(IllegalArgumentException.class, () -> {
            Kaprekar.countK(number);
        });
    }

    @ParameterizedTest
    @DisplayName("Тест для некорректных входных данных")
    @ValueSource(ints = {-100, 0, 151, 999, 1000, 10000, 10001, 1561654})
    void testCountKCornerCase(int number) {
        assertThrows(IllegalArgumentException.class, () -> {
            Kaprekar.countK(number);
        });
    }
}
