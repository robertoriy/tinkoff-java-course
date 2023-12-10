package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Spherical implements Transformation {
    @Override
    public Point apply(Point point) {
        double arg = 1.0 / norm(point);

        double x = arg * point.x();
        double y = arg * point.y();

        return new Point(x, y);
    }
}
