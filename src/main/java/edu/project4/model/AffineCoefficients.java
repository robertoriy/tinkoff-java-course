package edu.project4.model;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public record AffineCoefficients(double a, double b, double c, double d, double e, double f, Color color) {
    private static final int RGB_RANGE = 256;

    public static AffineCoefficients create() {
        while (true) {
            double a = ThreadLocalRandom.current().nextDouble(-1, 1);
            double b = ThreadLocalRandom.current().nextDouble(-1, 1);
            double c = ThreadLocalRandom.current().nextDouble(-1, 1);
            double d = ThreadLocalRandom.current().nextDouble(-1, 1);
            double e = ThreadLocalRandom.current().nextDouble(-1, 1);
            double f = ThreadLocalRandom.current().nextDouble(-1, 1);
            if (isCoefficientsValid(a, b, d, e)) {
                return new AffineCoefficients(a, b, c, d, e, f, randomColor());
            }
        }
    }

    private static boolean isCoefficientsValid(double a, double b, double d, double e) {
        return (a * a + d * d < 1)
            && (b * b + e * e < 1)
            && (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d));
    }

    private static Color randomColor() {
        int red = ThreadLocalRandom.current().nextInt(0, RGB_RANGE);
        int green = ThreadLocalRandom.current().nextInt(0, RGB_RANGE);
        int blue = ThreadLocalRandom.current().nextInt(0, RGB_RANGE);

        return new Color(red, green, blue);
    }
}
