package edu.hw1.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class SpecialPalindromeTest {
    @ParameterizedTest
    @DisplayName("Тест для значений палиндромов или имеющих потомков палиндромов")
    @ValueSource(ints = {0, 1, 121, 3223, 11_21_12_30, 13_00_11_20, 23_33_60_14, 11, 52_61})
    void testIsPalindromeDescendantTrueCase(int number) {
        boolean actual = SpecialPalindrome.isPalindromeDescendant(number);

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Тест для значений не палиндромов и не имеющих потомков палиндромов")
    @ValueSource(ints = {12, 11_13, 374, 52_63, 756, 45_64_52})
    void testIsPalindromeDescendantFalseCase(int number) {
        boolean actual = SpecialPalindrome.isPalindromeDescendant(number);

        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Тест для некорректных входных значений")
    @ValueSource(ints = {-1, -121, -2165})
    void testIsPalindromeDescendantInvalidInputCase(int number) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> SpecialPalindrome.isPalindromeDescendant(number));
    }
}
