package edu.hw1.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class CyclicBitShiftTest {
    @ParameterizedTest
    @DisplayName("Тест для правого сдвига")
    @CsvSource({
        "8, 1, 4",
        "128, 3, 16"
    })
    void testRotateRightNormalCase(int n, int shift, int expected) {
        int actual = CyclicBitShift.rotateRight(n, shift);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Тест для левого сдвига")
    @CsvSource({
        "16, 1, 1",
        "17, 2, 2"
    })
    void testRotateLeftNormalCase(int n, int shift, int expected) {
        int actual = CyclicBitShift.rotateLeft(n, shift);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Тест для некорректных входных данных правого сдвига")
    @CsvSource({
        "-16, 1",
        "-1, -2"
    })
    void testRotateRightBadInputCase(int n, int shift) {
        assertThrows(IllegalArgumentException.class, () -> {
            CyclicBitShift.rotateRight(n, shift);
        });
    }

    @ParameterizedTest
    @DisplayName("Тест для некорректных входных данных левого сдвига")
    @CsvSource({
        "-16, 1",
        "-1, -2"
    })
    void testRotateLeftBadInputCase(int n, int shift) {
        assertThrows(IllegalArgumentException.class, () -> {
            CyclicBitShift.rotateLeft(n, shift);
        });
    }
}
