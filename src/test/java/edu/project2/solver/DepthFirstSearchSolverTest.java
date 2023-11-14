package edu.project2.solver;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class DepthFirstSearchSolverTest {
    @Nested
    final class NormalCase {
        @ParameterizedTest
        @DisplayName("Тест на поиск маршрута в лабиринте")
        @MethodSource("provideMazeAndAnswer")
        void shouldFindPath(Maze maze, Coordinate start, Coordinate end, List<Coordinate> expected) {
            Solver solver = new DepthFirstSearchSolver();

            List<Coordinate> actual = solver.solve(maze, start, end);

            assertThat(actual).isEqualTo(expected);
        }

        private static Stream<Arguments> provideMazeAndAnswer() {
        /*
            (1,1) -> (0,3)
            ⬛️⬜️⬛️⬛️
            ⬛️⬜️⬜️⬛️
            ⬜️⬛️⬜️⬜️
            ⬜️⬜️⬜️⬛️

            ⬛️⬜️⬛️⬛️
            ⬛️🟣🟣⬛️
            ⬜️⬛️🟣⬜️
            🟣🟣🟣⬛️
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
                    new Coordinate(1, 1),
                    new Coordinate(3, 0),
                    path
                )
            );
        }

        @ParameterizedTest
        @DisplayName("Тест на невозможность найти маршрут в лабиринте - нет пути")
        @MethodSource("provideMazeAndEmptyAnswer")
        void shouldNotFindPath(Maze maze, Coordinate start, Coordinate end, List<Coordinate> expected) {
            Solver solver = new DepthFirstSearchSolver();

            List<Coordinate> actual = solver.solve(maze, start, end);

            assertThat(actual).isEqualTo(expected);
        }

        private static Stream<Arguments> provideMazeAndEmptyAnswer() {
        /*
            (1,1) -> (0,3)
            ⬛️⬜️⬛️⬛️
            ⬛️⬜️⬜️⬛️
            ⬜️⬛️⬜️⬜️
            ⬜️⬛️⬜️⬛️
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
                new Cell(3, 0, Cell.Type.PASSAGE), new Cell(3, 1, Cell.Type.WALL),
                new Cell(3, 2, Cell.Type.PASSAGE), new Cell(3, 3, Cell.Type.WALL)
            };

            return Stream.of(
                Arguments.of(
                    new Maze(4, 4, grid),
                    new Coordinate(1, 1),
                    new Coordinate(3, 0),
                    Collections.emptyList()
                )
            );
        }
    }

    @Nested
    final class InvalidInput {
        @ParameterizedTest
        @DisplayName("Тест на некорректный ввод точек для поиска (координаты - стены)")
        @MethodSource("provideWrongCoordinates")
        void shouldThrowIllegalArgumentException(Maze maze, Coordinate start, Coordinate end) {
            Solver solver = new DepthFirstSearchSolver();

            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> solver.solve(maze, start, end));
        }

        private static Stream<Arguments> provideWrongCoordinates() {
            /*
                ⬛️⬜️⬛️⬛️
                ⬛️⬜️⬜️⬛️
                ⬜️⬛️⬜️⬜️
                ⬜️⬜️⬜️⬛️
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
                    new Coordinate(0, 0),
                    new Coordinate(3, 0)
                ),
                Arguments.of(
                    new Maze(4, 4, grid),
                    new Coordinate(1, 1),
                    new Coordinate(0, 3)
                ),
                Arguments.of(
                    new Maze(4, 4, grid),
                    new Coordinate(1, 0),
                    new Coordinate(1, 0)
                )
            );
        }

    }
}
