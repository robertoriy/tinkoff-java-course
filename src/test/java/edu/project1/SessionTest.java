package edu.project1;

import edu.project1.controller.Session;
import edu.project1.guess.Defeat;
import edu.project1.guess.GuessState;
import edu.project1.guess.Hit;
import edu.project1.guess.Miss;
import edu.project1.guess.Victory;
import edu.project1.word.Word;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

final class SessionTest {
    @ParameterizedTest
    @DisplayName("Тест на корректное изменение состояния")
    @MethodSource("provideWordsAndGuessAndState")
    void testState(Word word, char guess, Class<? extends GuessState> expectedStateClass) {
        Session session = new Session(word);

        GuessState actualState = session.guess(guess);

        assertThat(actualState).isInstanceOf(expectedStateClass);
    }

    public static Stream<Arguments> provideWordsAndGuessAndState() {
        return Stream.of(
            Arguments.of(new Word("hello"), 'l', Hit.class),
            Arguments.of(new Word("aaaaa"), 'a', Victory.class),
            Arguments.of(new Word("hello"), 'g', Miss.class)
        );
    }

    @ParameterizedTest
    @DisplayName("Тест на получение индексов каждой буквы")
    @MethodSource("provideWordsAndGuess")
    void testGuess(Word word, char guess, String expectedHint) {
        Session session = new Session(word);

        session.guess(guess);
        String actualHint = session.getCurrentHint();

        assertThat(actualHint).isEqualTo(expectedHint);
    }

    public static Stream<Arguments> provideWordsAndGuess() {
        return Stream.of(
            Arguments.of(new Word("hello"), 'l', "**ll*"),
            Arguments.of(new Word("aabcaad"), 'a', "aa**aa*"),
            Arguments.of(new Word("hello"), 'g', "*****")
        );
    }

    @Test
    @DisplayName("Тест на проверку результата после сдачи")
    void testGiveUp() {
        Session session = new Session(new Word("word"));

        GuessState actualState = session.giveUp();

        assertThat(actualState).isInstanceOf(Defeat.class);
    }

}
