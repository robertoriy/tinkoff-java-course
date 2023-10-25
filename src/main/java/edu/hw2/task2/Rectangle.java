package edu.hw2.task2;

public sealed class Rectangle permits Square {
    private final double height;
    private final double width;

    public Rectangle() {
        this.height = 0;
        this.width = 0;
    }

    public Rectangle(double height, double width) {
        if (height < 0 || width < 0) {
            throw new IllegalArgumentException("Length values must be non-negative");
        }
        this.height = height;
        this.width = width;
    }

    public Rectangle setWidth(double width) {
        return new Rectangle(this.height, width);
    }

    public Rectangle setHeight(double height) {
        return new Rectangle(height, this.width);
    }

    public double area() {
        return width * height;
    }
}
