package edu.project1;

import edu.project1.word.Word;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class WordTest {
    @ParameterizedTest
    @DisplayName("Тест на индексы каждой буквы")
    @MethodSource("provideWordsAndIndexes")
    void testLetterIndexes(Word word, char c, List<Integer> expected) {
        List<Integer> actual = word.getLetterIndexes(c);

        assertThat(actual).isEqualTo(expected);
    }

    public static Stream<Arguments> provideWordsAndIndexes() {
        return Stream.of(
            Arguments.of(new Word("hello"), 'l', List.of(2, 3)),
            Arguments.of(new Word("aabcaad"), 'a', List.of(0, 1, 4, 5))
        );
    }

    @ParameterizedTest
    @DisplayName("Тест на наличие буквы в слове")
    @MethodSource("provideWordsAndLetters")
    void testContains(Word word, char c, boolean expected) {
        boolean actual = word.contains(c);

        assertThat(actual).isEqualTo(expected);
    }

    public static Stream<Arguments> provideWordsAndLetters() {
        return Stream.of(
            Arguments.of(new Word("hello"), 'o', true),
            Arguments.of(new Word("hello"), 'g', false),
            Arguments.of(new Word("hello"), 'l', true)
        );
    }

    @ParameterizedTest
    @DisplayName("Тест на длину слова")
    @MethodSource("provideWordsAndLengths")
    void testLength(Word word, int expectedLength) {
        int actualLength = word.getLength();

        assertThat(actualLength).isEqualTo(expectedLength);
    }

    public static Stream<Arguments> provideWordsAndLengths() {
        return Stream.of(
            Arguments.of(new Word("hello"), 5),
            Arguments.of(new Word("cat"), 3),
            Arguments.of(new Word("abcdefg"), 7)
        );
    }

    @Nested
    class InvalidInput {
        @ParameterizedTest
        @DisplayName("Тест на некорректный ввод")
        @MethodSource("provideInvalidInput")
        void testInvalidInput(String invalidWord) {
            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Word(invalidWord));
        }

        public static Stream<Arguments> provideInvalidInput() {
            return Stream.of(
                Arguments.of((Object) null),
                Arguments.of(""),
                Arguments.of("ab")
            );
        }
    }
}
