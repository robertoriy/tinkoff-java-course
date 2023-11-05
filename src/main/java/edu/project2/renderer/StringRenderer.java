package edu.project2.renderer;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.List;

public class StringRenderer implements Renderer {
    private static final String PASSAGE_SYMBOL = "⬜️";
    private static final String WALL_SYMBOL = "⬛️";
    private static final String PATH_SYMBOL = "🟣";

    @Override
    public String render(Maze maze) {
        StringBuilder rendered = new StringBuilder();

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell currentCell = maze.getCell(i, j);
                rendered.append(getSymbol(currentCell.type()));
            }
            rendered.append('\n');
        }
        return rendered.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        if (isInvalidPath(path, maze)) {
            throw new IllegalArgumentException("Invalid path");
        }
        StringBuilder rendered = new StringBuilder();

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (path.contains(new Coordinate(i, j))) {
                    rendered.append(PATH_SYMBOL);
                    continue;
                }
                Cell currentCell = maze.getCell(i, j);
                rendered.append(getSymbol(currentCell.type()));
            }
            rendered.append('\n');
        }
        return rendered.toString();
    }

    private boolean isInvalidPath(List<Coordinate> path, Maze maze) {
        for (Coordinate coordinate : path) {
            if (!maze.contains(coordinate)) {
                return true;
            }
        }
        return false;
    }

    private String getSymbol(Cell.Type type) {
        return switch (type) {
            case PASSAGE -> PASSAGE_SYMBOL;
            case WALL -> WALL_SYMBOL;
        };
    }
}
