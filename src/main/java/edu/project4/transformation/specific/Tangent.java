package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Tangent implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = Math.sin(point.x()) / Math.cos(point.y());
        double y = Math.tan(point.y());

        return new Point(x, y);
    }
}
