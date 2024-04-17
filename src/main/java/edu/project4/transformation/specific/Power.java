package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Power implements Transformation {
    @Override
    public Point apply(Point point) {
        double theta = theta(point);
        double sinTheta = Math.sin(theta);
        double arg = Math.pow(norm(point), sinTheta);

        double x = arg * Math.cos(theta);
        double y = arg * sinTheta;

        return new Point(x, y);
    }
}
