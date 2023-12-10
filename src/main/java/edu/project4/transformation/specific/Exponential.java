package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Exponential implements Transformation {
    @Override
    public Point apply(Point point) {
        double arg = point.x() - 1;
        double delta = Math.PI * point.y();

        double x = Math.exp(arg) * Math.cos(delta);
        double y = Math.exp(arg) * Math.sin(delta);

        return new Point(x, y);
    }
}
