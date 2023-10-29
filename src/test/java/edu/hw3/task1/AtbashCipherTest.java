package edu.hw3.task1;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class AtbashCipherTest {
    @ParameterizedTest
    @DisplayName("Тест на корректную работу с латинскими буквами из примера")
    @CsvSource({
        "Hello world!, Svool dliow!",
        "Any fool can write code that a computer can understand. " +
            "Good programmers write code that humans can understand. ― Martin Fowler, " +
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. " +
            "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
    })
    void testAtbashLatinLettersCase(String input, String expected) {
        String actual = AtbashCipher.atbash(input);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Тест на корректную работу с нелатинскими буквами")
    @CsvSource({
        "Hello привет world!, Svool привет dliow!",
        "Any fool тест can write code that a computer can understand. " +
            "Good programmers write code that humans can understand. ― Martin Fowler, " +
            "Zmb ullo тест xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. " +
            "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
    })
    void testAtbashNoLatinLettersCase(String input, String expected) {
        String actual = AtbashCipher.atbash(input);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Тест на исключение из-за некорректного ввода")
    @MethodSource("provideInvalidInput")
    void testAtbashInvalidInputCase(String input) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> AtbashCipher.atbash(input));
    }

    private static Stream<Arguments> provideInvalidInput() {
        return Stream.of(
            Arguments.of((Object) null),
            Arguments.of(""),
            Arguments.of("         ")
        );
    }
}
