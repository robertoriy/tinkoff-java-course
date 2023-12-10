package edu.project4.model;

import java.util.concurrent.ThreadLocalRandom;

public record Rectangle(double x, double y, double width, double height) {
    public boolean contains(Point point) {
        return point.x() >= x && point.x() < width + x
            && point.y() >= y && point.y() < height + y;
    }

    public Point randomPoint() {
        double randomX = ThreadLocalRandom.current().nextDouble(0, width);
        double randomY = ThreadLocalRandom.current().nextDouble(0, height);
        return new Point(randomX, randomY);
    }
}
