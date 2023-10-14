package edu.hw1.task7;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class CyclicBitShiftTest {
    @ParameterizedTest
    @DisplayName("Тест для правого сдвига")
    @MethodSource("provideTestCasesForRotateRight")
    void testRotateRightNormalCase(int n, int shift, int expected) {
        int actual = CyclicBitShift.rotateRight(n, shift);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTestCasesForRotateRight() {
        return Stream.of(
            Arguments.of(
                Integer.parseInt("1000", 2), 1, Integer.parseInt("0100", 2)
            ),
            Arguments.of(
                Integer.parseInt("1000", 2), -8, Integer.parseInt("1000", 2)
            ),
            Arguments.of(
                Integer.parseInt("10111", 2), -8, Integer.parseInt("11101", 2)
            ),
            Arguments.of(
                Integer.parseInt("10000000", 2), 3, Integer.parseInt("00010000", 2)
            ),
            Arguments.of(
                Integer.parseInt("1100101011", 2), 3, Integer.parseInt("0111100101", 2)
            ),
            Arguments.of(
                Integer.parseInt("1100101011", 2), 6, Integer.parseInt("1010111100", 2)
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест для левого сдвига")
    @MethodSource("provideTestCasesForRotateLeft")
    void testRotateLeftNormalCase(int n, int shift, int expected) {
        int actual = CyclicBitShift.rotateLeft(n, shift);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTestCasesForRotateLeft() {
        return Stream.of(
            Arguments.of(
                Integer.parseInt("10000", 2), 1, Integer.parseInt("00001", 2)
            ),
            Arguments.of(
                Integer.parseInt("10001", 2), 2, Integer.parseInt("00110", 2)
            ),
            Arguments.of(
                Integer.parseInt("1000", 2), -8, Integer.parseInt("1000", 2)
            ),
            Arguments.of(
                Integer.parseInt("10111", 2), -8, Integer.parseInt("11110", 2)
            ),
            Arguments.of(
                Integer.parseInt("1100101011", 2), 14, Integer.parseInt("1010111100", 2)
            ),
            Arguments.of(
                Integer.parseInt("1100101011", 2), 8, Integer.parseInt("1111001010", 2)
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест для некорректных входных данных")
    @CsvSource({
        "-16, 1",
        "-1, -2",
        "0, 23",
        "-425, 3"
    })
    void testRotateInvalidInputCase(int n, int shift) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> CyclicBitShift.rotateLeft(n, shift));

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> CyclicBitShift.rotateRight(n, shift));
    }
}
