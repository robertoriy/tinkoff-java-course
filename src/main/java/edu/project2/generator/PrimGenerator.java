package edu.project2.generator;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimGenerator implements Generator {
    private static final int NO_STEP = 0;
    private static final int STEP = 1;

    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);
        boolean[][] visited = new boolean[height][width];
        List<Cell> walls = new ArrayList<>();

        makePassage(new Coordinate(1, 1), maze, visited, walls);

        while (!walls.isEmpty()) {
            Collections.shuffle(walls);
            Cell randomCell = walls.get(0);

            List<Cell> neighbours = getNeighbours(maze, randomCell.row(), randomCell.column());

            int visitedNeighbours = 0;
            for (Cell neighbour : neighbours) {
                if (visited[neighbour.row()][neighbour.column()]) {
                    visitedNeighbours++;
                }
            }

            if (visitedNeighbours == 1 && maze.contains(randomCell.row(), randomCell.column())) {
                makePassage(new Coordinate(randomCell.row(), randomCell.column()), maze, visited, walls);
            }

            walls.remove(randomCell);
        }
        return maze;
    }

    private List<Cell> getNeighbours(Maze maze, int row, int column) {
        final int[][] neighboursCoordinates = {
            {STEP, NO_STEP},
            {-STEP, NO_STEP},
            {NO_STEP, STEP},
            {NO_STEP, -STEP}
        };
        List<Cell> neighbours = new ArrayList<>();

        for (int[] neighbour : neighboursCoordinates) {
            int newRow = row + neighbour[0];
            int newColumn = column + neighbour[1];

            if (maze.contains(newRow, newColumn)) {
                neighbours.add(maze.getCell(newRow, newColumn));
            }
        }
        return neighbours;
    }

    private void makePassage(Coordinate coordinate, Maze maze, boolean[][] visited, List<Cell> walls) {
        visited[coordinate.row()][coordinate.column()] = true;
        maze.setCellType(coordinate.row(), coordinate.column(), Cell.Type.PASSAGE);

        if (coordinate.row() != maze.getHeight() - 1 && coordinate.column() != maze.getWidth() - 1) {
            List<Cell> neighbours = getNeighbours(maze, coordinate.row(), coordinate.column());
            walls.addAll(neighbours);
        }
    }
}
