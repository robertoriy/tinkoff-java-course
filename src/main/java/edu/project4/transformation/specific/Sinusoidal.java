package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Sinusoidal implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = Math.sin(point.x());
        double y = Math.sin(point.y());

        return new Point(x, y);
    }
}
