package edu.project2.solver;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class DepthFirstSearchSolver implements Solver {
    private static final int NO_STEP = 0;
    private static final int STEP = 1;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (isInvalidCoordinates(maze, start, end)) {
            throw new IllegalArgumentException("Invalid coordinates: out of maze or not passage");
        }
        return dfs(maze, start, end);
    }

    private List<Coordinate> dfs(Maze maze, Coordinate start, Coordinate end) {
        boolean[][] visitedCells = new boolean[maze.getHeight()][maze.getWidth()];
        Deque<Coordinate> stack = new ArrayDeque<>();

        visitedCells[start.row()][start.column()] = true;
        stack.push(start);

        while (!stack.isEmpty()) {
            Coordinate current = stack.peek();
            if (current.equals(end)) {
                return new ArrayList<>(stack).reversed();
            }
            List<Coordinate> neighbours = getNeighboursWithPassage(maze, current);

            boolean found = false;
            for (Coordinate neighbour : neighbours) {
                if (!visitedCells[neighbour.row()][neighbour.column()]) {
                    visitedCells[neighbour.row()][neighbour.column()] = true;
                    stack.push(neighbour);
                    found = true;
                    break;
                }
            }
            if (!found) {
                stack.pop();
            }
        }
        return Collections.emptyList();
    }

    private List<Coordinate> getNeighboursWithPassage(Maze maze, Coordinate current) {
        final int[][] neighboursCoordinates = {
            {STEP, NO_STEP},
            {-STEP, NO_STEP},
            {NO_STEP, STEP},
            {NO_STEP, -STEP}
        };
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
