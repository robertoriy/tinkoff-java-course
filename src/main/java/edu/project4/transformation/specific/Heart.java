package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Heart implements Transformation {
    @Override
    public Point apply(Point point) {
        double norm = norm(point);
        double arg = norm * theta(point);

        double x = norm * Math.sin(arg);
        double y = -norm * Math.cos(arg);

        return new Point(x, y);
    }
}
