package edu.hw1.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class BrokenStringTest {
    @ParameterizedTest
    @DisplayName("Тест для обычных строк")
    @CsvSource({
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "123456, 214365",
        "badce, abcde",
        "wxwxwx, xwxwxw"
    })
    void testFixStringNormalCase(String input, String expected) {
        String actual = BrokenString.fixString(input);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест для пустой строки и строки из одного символа")
    void testFixStringSpecialCase() {
        String actualEmpty = BrokenString.fixString("");
        String actualOne = BrokenString.fixString("x");

        assertThat(actualEmpty).isEmpty();
        assertThat(actualOne).isEqualTo("x");
    }

    @Test
    @DisplayName("Тест для null входных данных")
    void testFixStringInvalidInputCase() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> BrokenString.fixString(null));
    }
}
