package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Handkerchief implements Transformation {
    @Override
    public Point apply(Point point) {
        double norm = norm(point);
        double theta = theta(point);

        double x = norm * Math.sin(theta + norm);
        double y = norm * Math.cos(theta - norm);

        return new Point(x, y);
    }
}
