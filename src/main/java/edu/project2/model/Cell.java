package edu.project2.model;

public record Cell(int row, int column, Type type) {
    public Cell(int row, int column, Type type) {
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException("Coordinate must contain not negative number");
        }
        if (type == null) {
            throw new IllegalArgumentException("Type must be not null");
        }
        this.row = row;
        this.column = column;
        this.type = type;
    }

    public enum Type {
        WALL, PASSAGE
    }
}
