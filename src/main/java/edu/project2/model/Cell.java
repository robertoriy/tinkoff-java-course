package edu.project2.model;

public record Cell(int row, int col, Type type) {
    public enum Type { WALL, PASSAGE }
}
