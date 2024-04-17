package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Hyperbolic implements Transformation {
    @Override
    public Point apply(Point point) {
        double norm = norm(point);
        double theta = theta(point);

        double x = Math.sin(theta) / norm;
        double y = norm * Math.cos(theta);

        return new Point(x, y);
    }
}
