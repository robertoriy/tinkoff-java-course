package edu.hw1.task8;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
            }),
            Arguments.of((Object) new int[][] {
                {0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0}
            }),
            Arguments.of((Object) new int[][] {
                {0, 1, 0, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 1},
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
            }),
            Arguments.of((Object) new int[][] {
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1}
            })
        );
    }

    @ParameterizedTest
    @DisplayName("Тест для некорректных входных данных")
    @MethodSource("provideTestCasesForInvalidInput")
    void testKnightBoardCaptureInvalidInputCase(int[][] board) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> ChessBoard.knightBoardCapture(board));
    }

    private static Stream<Arguments> provideTestCasesForInvalidInput() {
        return Stream.of(
            Arguments.of((Object) new int[][] {
                {},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 1, 0},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 1}
            }),
            Arguments.of((Object) new int[][] {
                {9, 5, 0, 0, 1, 0, 0, 0},
                {9, 0, 0, 3, 0, 1, 0, 0},
                {9, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {4, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {9, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}
            }),
            Arguments.of((Object) new int[][] {
                null,
                {9, 0, 0, 3, 0, 1, 0, 0},
                {9, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                null,
                {0, 0, 0, 0, 0, 1, 0, 0},
                {9, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}
            }),
            Arguments.of((Object) null)
        );
    }
}
