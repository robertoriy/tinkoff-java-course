package edu.project2.model;

public final class Maze {
    private static final int DEFAULT_HEIGHT = 10;
    private static final int DEFAULT_WIDTH = 10;
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze() {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    public Maze(int height, int width) {
        validatePositiveNumber(height, width);

        this.height = height;
        this.width = width;
        grid = fillAllWithWall();
    }

    public Maze(int height, int width, Cell[][] grid) {
        validatePositiveNumber(height, width);
        validateGrid(grid, height, width);

        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getCell(int row, int column) {
        return grid[row][column];
    }

    public boolean contains(int row, int column) {
        return row >= 0 && row < height && column >= 0 && column < width;
    }

    public boolean contains(Coordinate coordinate) {
        return contains(coordinate.row(), coordinate.column());
    }

    public Cell.Type getCellType(int row, int column) {
        if (!contains(row, column)) {
            throw new IllegalArgumentException("Coordinate out of maze");
        }
        return grid[row][column].type();
    }

    public Cell.Type getCellType(Coordinate coordinate) {
        return getCellType(coordinate.row(), coordinate.column());
    }

    public void setCellType(int row, int column, Cell.Type type) {
        grid[row][column] = new Cell(row, column, type);
    }

    private void validatePositiveNumber(int height, int width) {
        if (height < 1 || width < 1) {
            throw new IllegalArgumentException("Value 'height/width' must be positive");
        }
    }

    private void validateGrid(Cell[][] grid, int height, int width) {
        if (grid.length != height) {
            throw new IllegalArgumentException("Invalid grid - wrong height");
        }
        for (Cell[] row : grid) {
            if (row.length != width) {
                throw new IllegalArgumentException("Invalid grid - wrong width");
            }
        }
    }

    private Cell[][] fillAllWithWall() {
        Cell[][] newGrid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newGrid[i][j] = new Cell(i, j, Cell.Type.WALL);
            }
        }
        return newGrid;
    }
}
