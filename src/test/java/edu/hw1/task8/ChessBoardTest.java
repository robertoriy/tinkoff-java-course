package edu.hw1.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

final class ChessBoardTest {
    @ParameterizedTest
    @DisplayName("Тест для случая, где ни один конь не может захватить другого")
    @MethodSource("provideTestCasesForTrueScenarios")
    void testKnightBoardCaptureTrueCase(int[][] board) {
        boolean actual = ChessBoard.knightBoardCapture(board);

        assertThat(actual).isTrue();
    }

    private static Stream<Arguments> provideTestCasesForTrueScenarios() {
        return Stream.of(
            Arguments.of((Object) new int[][] {
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 0}
            })
        );
    }

    @ParameterizedTest
    @DisplayName("Тест для случая, где кони могут захватить друг друга")
    @MethodSource("provideTestCasesForFalseScenarios")
    void testKnightBoardCaptureFalseCase(int[][] board) {
        boolean actual = ChessBoard.knightBoardCapture(board);

        assertThat(actual).isFalse();
    }

    private static Stream<Arguments> provideTestCasesForFalseScenarios() {
        return Stream.of(
            Arguments.of((Object) new int[][] {
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1}
            }),
            Arguments.of((Object) new int[][] {
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}
            })
        );
    }
}
