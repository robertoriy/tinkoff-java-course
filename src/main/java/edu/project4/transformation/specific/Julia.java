package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Julia implements Transformation {
    private static final double JULIA_CONST = 2.0;

    @Override
    public Point apply(Point point) {
        double sqrtNorm = Math.sqrt(norm(point));
        double theta = theta(point);
        double arg = theta / JULIA_CONST;

        double x = sqrtNorm * Math.cos(arg);
        double y = sqrtNorm * Math.sin(arg);

        return new Point(x, y);
    }
}
