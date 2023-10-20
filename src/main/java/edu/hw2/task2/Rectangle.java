package edu.hw2.task2;

public class Rectangle {
    private final double height;
    private final double width;

    public Rectangle() {
        this.height = 0;
        this.width = 0;
    }

    public Rectangle(double height, double width) {
        validateNonNegativeValue(height);
        validateNonNegativeValue(width);

        this.height = height;
        this.width = width;
    }

    Rectangle setWidth(double width) {
        validateNonNegativeValue(width);
        return new Rectangle(this.height, width);
    }

    Rectangle setHeight(double height) {
        validateNonNegativeValue(height);
        return new Rectangle(height, this.width);
    }

    double area() {
        return width * height;
    }

    private void validateNonNegativeValue(double length) {
        if (length < 0) {
            throw new IllegalArgumentException("Length value must be non-negative");
        }
    }
}
