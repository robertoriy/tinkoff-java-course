package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Disc implements Transformation {
    @Override
    public Point apply(Point point) {
        double delta = Math.PI * norm(point);
        double temp = theta(point) / Math.PI;

        double x = temp * Math.sin(delta);
        double y = temp * Math.cos(delta);

        return new Point(x, y);
    }
}
