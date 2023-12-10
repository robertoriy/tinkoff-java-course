package edu.project4.transformation.specific;

import edu.project4.model.Point;
import edu.project4.transformation.Transformation;

public final class Swirl implements Transformation {
    @Override
    public Point apply(Point point) {
        double squareNorm = point.x() * point.x() + point.y() * point.y();
        double sinSquareNorm = Math.sin(squareNorm);
        double cosSquareNorm = Math.cos(squareNorm);

        double x = point.x() * sinSquareNorm - point.y() * cosSquareNorm;
        double y = point.x() * cosSquareNorm + point.y() * sinSquareNorm;

        return new Point(x, y);
    }
}
