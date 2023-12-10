package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class FishEye implements Transformation {
    @Override
    public Point apply(Point point) {
        double norm = norm(point);
        double temp = 2.0 / (norm + 1.0);

        double x = temp * point.y();
        double y = temp * point.x();

        return new Point(x, y);
    }
}
