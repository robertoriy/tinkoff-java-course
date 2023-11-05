package edu.project2.solver;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepthFirstSearchSolver implements Solver {
    private Maze maze;
    private boolean[][] visitedCells;
    private List<Coordinate> path;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (isInvalidCoordinates(maze, start, end)) {
            throw new IllegalArgumentException("Invalid coordinates: out of maze or not passage");
        }
        update(maze);
        if (!dfs(start, end)) {
            return Collections.emptyList();
        }
        return path;
    }

    private boolean dfs(Coordinate current, Coordinate end) {
        visitedCells[current.row()][current.column()] = true;
        path.add(current);
        if (current.equals(end)) {
            return true;
        }
        List<Coordinate> neighbours = getNeighboursWithPassage(current);
        for (Coordinate neighbour : neighbours) {
            if (!visitedCells[neighbour.row()][neighbour.column()] && dfs(neighbour, end)) {
                return true;
            }
        }
        path.removeLast();
        return false;
    }

    private void update(Maze maze) {
        this.maze = maze;
        visitedCells = new boolean[maze.getHeight()][maze.getWidth()];
        path = new ArrayList<>();
    }

    private List<Coordinate> getNeighboursWithPassage(Coordinate current) {
        final int[][] neighboursCoordinates = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        List<Coordinate> neighbours = new ArrayList<>();

        for (int[] neighbour : neighboursCoordinates) {
            int newRow = current.row() + neighbour[0];
            int newColumn = current.column() + neighbour[1];

            if (maze.contains(newRow, newColumn) && maze.getCellType(newRow, newColumn) == Cell.Type.PASSAGE) {
                neighbours.add(new Coordinate(newRow, newColumn));
            }
        }
        return neighbours;
    }

    private boolean isInvalidCoordinates(Maze maze, Coordinate start, Coordinate end) {
        return maze.getCellType(start) == Cell.Type.WALL || maze.getCellType(end) == Cell.Type.WALL;
    }
}
