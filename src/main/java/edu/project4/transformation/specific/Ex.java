package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Ex implements Transformation {
    @Override
    public Point apply(Point point) {
        double norm = norm(point);
        double theta = theta(point);

        double p0 = Math.sin(theta + norm);
        double p1 = Math.cos(theta - norm);
        p0 = p0 * p0 * p0;
        p1 = p1 * p1 * p1;

        double x = norm * (p0 + p1);
        double y = norm * (p0 - p1);

        return new Point(x, y);
    }
}
