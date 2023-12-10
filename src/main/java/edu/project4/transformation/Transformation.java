package edu.project4.transformation;

import edu.project4.model.Point;
import java.util.function.Function;

public interface Transformation extends Function<Point, Point> {
    default double norm(Point point) {
        return Math.sqrt(point.x() * point.x() + point.y() * point.y());
    }

    default double theta(Point point) {
        return Math.atan(point.x() / point.y());
    }

    default double phi(Point point) {
        return Math.atan(point.y() / point.x());
    }
}
