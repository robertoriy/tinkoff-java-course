package edu.project2.renderer;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class StringRendererTest {
    @Nested
    final class NormalCase {
        @ParameterizedTest
        @DisplayName("Тест на рендер заданного лабиринта")
        @MethodSource("provideMazeAndView")
        void shouldRenderMaze(Maze maze, String expected) {
            StringRenderer renderer = new StringRenderer();

            String actual = renderer.render(maze);

            assertThat(actual).isEqualTo(expected);
        }

        private static Stream<Arguments> provideMazeAndView() {
        /*
        ______
        |# ##|
        |#  #|
        | # #|
        |   #|
        ------
         */
            Cell[][] grid = new Cell[4][4];
            grid[0] = new Cell[] {
                new Cell(0, 0, Cell.Type.WALL), new Cell(0, 1, Cell.Type.PASSAGE),
                new Cell(0, 2, Cell.Type.WALL), new Cell(0, 3, Cell.Type.WALL)
            };
            grid[1] = new Cell[] {
                new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE),
                new Cell(1, 2, Cell.Type.PASSAGE), new Cell(1, 3, Cell.Type.WALL)
            };
            grid[2] = new Cell[] {
                new Cell(2, 0, Cell.Type.PASSAGE), new Cell(2, 1, Cell.Type.WALL),
                new Cell(2, 2, Cell.Type.PASSAGE), new Cell(2, 3, Cell.Type.PASSAGE)
            };
            grid[3] = new Cell[] {
                new Cell(3, 0, Cell.Type.PASSAGE), new Cell(3, 1, Cell.Type.PASSAGE),
                new Cell(3, 2, Cell.Type.PASSAGE), new Cell(3, 3, Cell.Type.WALL)
            };
            return Stream.of(
                Arguments.of(
                    new Maze(4, 4, grid),
                    """
                        ⬛️⬜️⬛️⬛️
                        ⬛️⬜️⬜️⬛️
                        ⬜️⬛️⬜️⬜️
                        ⬜️⬜️⬜️⬛️
                        """
                )
            );
        }

        @ParameterizedTest
        @DisplayName("Тест на рендер заданного лабиринта с заданным ответом")
        @MethodSource("provideMazeWithAnswerAndView")
        void shouldRenderMazeWithAnswer(Maze maze, List<Coordinate> path, String expected) {
            StringRenderer renderer = new StringRenderer();

            String actual = renderer.render(maze, path);

            assertThat(actual).isEqualTo(expected);
        }

        private static Stream<Arguments> provideMazeWithAnswerAndView() {
        /*
        ______
        |# ##|
        |#xx#|
        | #x#|
        |xxx#|
        ------
         */
            Cell[][] grid = new Cell[4][4];
            grid[0] = new Cell[] {
                new Cell(0, 0, Cell.Type.WALL), new Cell(0, 1, Cell.Type.PASSAGE),
                new Cell(0, 2, Cell.Type.WALL), new Cell(0, 3, Cell.Type.WALL)
            };
            grid[1] = new Cell[] {
                new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE),
                new Cell(1, 2, Cell.Type.PASSAGE), new Cell(1, 3, Cell.Type.WALL)
            };
            grid[2] = new Cell[] {
                new Cell(2, 0, Cell.Type.PASSAGE), new Cell(2, 1, Cell.Type.WALL),
                new Cell(2, 2, Cell.Type.PASSAGE), new Cell(2, 3, Cell.Type.PASSAGE)
            };
            grid[3] = new Cell[] {
                new Cell(3, 0, Cell.Type.PASSAGE), new Cell(3, 1, Cell.Type.PASSAGE),
                new Cell(3, 2, Cell.Type.PASSAGE), new Cell(3, 3, Cell.Type.WALL)
            };
            List<Coordinate> path = List.of(
                new Coordinate(1, 1), new Coordinate(1, 2),
                new Coordinate(2, 2),
                new Coordinate(3, 2), new Coordinate(3, 1), new Coordinate(3, 0)
            );

            return Stream.of(
                Arguments.of(
                    new Maze(4, 4, grid),
                    path,
                    """
                        ⬛️⬜️⬛️⬛️
                        ⬛️🟣🟣⬛️
                        ⬜️⬛️🟣⬜️
                        🟣🟣🟣⬛️
                        """
                )
            );
        }
    }

    @Nested
    final class InvalidInput {
        @ParameterizedTest
        @DisplayName("Тест на рендер заданного лабиринта с невалидным ответом")
        @MethodSource("provideMazeWithWrongAnswer")
        void shouldThrowIllegalArgumentException(Maze maze, List<Coordinate> path) {
            StringRenderer renderer = new StringRenderer();

            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> renderer.render(maze, path));
        }

        private static Stream<Arguments> provideMazeWithWrongAnswer() {
        /*
        ______
        |# ##|
        |#xx#|
        | #xxx - error
        |xxx#|
        ------
         */
            Cell[][] grid = new Cell[4][4];
            grid[0] = new Cell[] {
                new Cell(0, 0, Cell.Type.WALL), new Cell(0, 1, Cell.Type.PASSAGE),
                new Cell(0, 2, Cell.Type.WALL), new Cell(0, 3, Cell.Type.WALL)
            };
            grid[1] = new Cell[] {
                new Cell(1, 0, Cell.Type.WALL), new Cell(1, 1, Cell.Type.PASSAGE),
                new Cell(1, 2, Cell.Type.PASSAGE), new Cell(1, 3, Cell.Type.WALL)
            };
            grid[2] = new Cell[] {
                new Cell(2, 0, Cell.Type.PASSAGE), new Cell(2, 1, Cell.Type.WALL),
                new Cell(2, 2, Cell.Type.PASSAGE), new Cell(2, 3, Cell.Type.PASSAGE)
            };
            grid[3] = new Cell[] {
                new Cell(3, 0, Cell.Type.PASSAGE), new Cell(3, 1, Cell.Type.PASSAGE),
                new Cell(3, 2, Cell.Type.PASSAGE), new Cell(3, 3, Cell.Type.WALL)
            };
            List<Coordinate> path = List.of(
                new Coordinate(1, 1), new Coordinate(1, 2),
                new Coordinate(2, 2), new Coordinate(2, 3), new Coordinate(2, 4),
                new Coordinate(3, 2), new Coordinate(3, 1), new Coordinate(3, 0)
            );

            return Stream.of(
                Arguments.of(
                    new Maze(4, 4, grid),
                    path
                )
            );
        }
    }
}
