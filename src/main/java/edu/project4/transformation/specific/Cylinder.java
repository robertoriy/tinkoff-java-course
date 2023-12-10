package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Cylinder implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = Math.sin(point.x());

        return new Point(x, point.y());
    }
}
