package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Polar implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = theta(point) / Math.PI;
        double y = norm(point) - 1;

        return new Point(x, y);
    }
}
