package edu.project4.renderer;

import edu.project4.model.AffineCoefficients;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import java.awt.Color;

public abstract class AbstractRenderer implements Renderer {
    protected static final int DEFAULT_STEP_START = -30;

    protected AffineCoefficients[] randomAffineCoefficients(int samples) {
        AffineCoefficients[] coefficients = new AffineCoefficients[samples];
        for (int i = 0; i < samples; i++) {
            coefficients[i] = AffineCoefficients.create();
        }
        return coefficients;
    }

    protected Point affine(Point point, AffineCoefficients coefficients) {
        double x = coefficients.a() * point.x()
            + coefficients.b() * point.y()
            + coefficients.c();

        double y = coefficients.d() * point.x()
            + coefficients.e() * point.y()
            + coefficients.f();

        return new Point(x, y);
    }

    protected Point rotatePoint(Point point, double delta) {
        double xRotated = point.x() * Math.cos(delta) - point.y() * Math.sin(delta);
        double yRotated = point.x() * Math.sin(delta) + point.y() * Math.cos(delta);
        return new Point(xRotated, yRotated);
    }

    protected void setColor(Pixel pixel, Color color) {
        if (pixel.hitCount() == 0) {
            pixel.red(color.getRed());
            pixel.green(color.getGreen());
            pixel.blue(color.getBlue());
        } else {
            pixel.red((pixel.red() + color.getRed()) / 2);
            pixel.green((pixel.green() + color.getGreen()) / 2);
            pixel.blue((pixel.blue() + color.getBlue()) / 2);
        }
    }
}
