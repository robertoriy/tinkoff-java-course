package edu.project1;

import edu.project1.dictionary.Dictionary;
import edu.project1.dictionary.HandwrittenDictionary;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class HandwrittenDictionaryTest {
    @ParameterizedTest
    @DisplayName("Тест на получение случайного слова из словаря")
    @MethodSource("provideRandomWordCase")
    void testRandomWord(String[] words) {
        Dictionary dictionary = new HandwrittenDictionary(words);

        String randomWord = dictionary.getRandomWord();

        assertThat(randomWord).isIn(words);
    }

    public static Stream<Arguments> provideRandomWordCase() {
        return Stream.of(
            Arguments.of((Object) new String[] {"hello", "cat", "world"}),
            Arguments.of((Object) new String[] {"third", "first", "second"})
        );
    }

    @ParameterizedTest
    @DisplayName("Тест на исключение при попытки создать словарь используя некорректные слова")
    @MethodSource("provideInvalidInputCase")
    void testInvalidInputCase(String[] words) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new HandwrittenDictionary(words));
    }

    public static Stream<Arguments> provideInvalidInputCase() {
        return Stream.of(
            Arguments.of((Object) new String[] {"hello", "aa", "aaaaaa"}),
            Arguments.of((Object) new String[] {"hello", "a", "aaaaaa"}),
            Arguments.of((Object) new String[] {"hello", "", "aaaaaa"}),
            Arguments.of((Object) new String[] {"hello", null, "aaaaaa"}),
            Arguments.of((Object) null)
        );
    }
}
