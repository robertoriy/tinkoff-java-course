package edu.project2.model;

public record Coordinate(int row, int column) {
    public Coordinate(int row, int column) {
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException("Coordinate must contain not negative number");
        }
        this.row = row;
        this.column = column;
    }
}
