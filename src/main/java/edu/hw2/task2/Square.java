package edu.hw2.task2;

public final class Square extends Rectangle {
    public Square() {
    }

    public Square(double side) {
        super(side, side);
    }

    public Square setSide(double side) {
        return new Square(side);
    }
}
