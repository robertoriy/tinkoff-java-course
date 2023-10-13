package edu.hw1.task1;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

final class VideoLengthTest {
    @ParameterizedTest
    @DisplayName("Тест для хороших входных данных")
    @CsvSource({
        "00:00, 0",
        "00:50, 50",
        "01:00, 60",
        "01:25, 85",
        "13:56, 836",
        "130:56, 7856",
        "9999:59, 599999"
    })
    void testMinutesToSecondsNormalCase(String time, int expected) {
        int actual = VideoLength.minutesToSeconds(time);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Тест для входных данных неудовлетворяющих требованию mm:ss")
    @CsvSource({
        "7:8, -1",
        "7:50, -1",
        "01:5, -1",
        "00:77, -1",
        "01:60, -1",
        "12:99, -1"
    })
    void testMinutesToSecondsBadFormatCase(String time, int expected) {
        int actual = VideoLength.minutesToSeconds(time);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Тест для больших значений входных и выходных данных")
    @CsvSource({
        "2147483647:59, -1",
        "100000000:50, -1"
    })
    void testMinutesToSecondsEdgeCase(String time, int expected) {
        int actual = VideoLength.minutesToSeconds(time);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Тест для некорректных входных данных")
    @CsvSource({
        "mm:ss, -1",
        "abc:de, -1",
        "wasdqwer, -1",
        ", -1"
    })
    void testMinutesToSecondsBadInputCase(String time, int expected) {
        int actual = VideoLength.minutesToSeconds(time);

        assertThat(actual).isEqualTo(expected);
    }
}
