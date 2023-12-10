package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Diamond implements Transformation {
    @Override
    public Point apply(Point point) {
        double norm = norm(point);
        double theta = theta(point);

        double x = Math.sin(theta) * Math.cos(norm);
        double y = Math.cos(theta) * Math.sin(norm);

        return new Point(x, y);
    }
}
