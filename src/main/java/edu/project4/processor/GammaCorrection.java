package edu.project4.processor;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;

public class GammaCorrection implements ImageProcessor {
    private static final double GAMMA = 2.15;
    private static final double EXPONENT = 1.0 / GAMMA;

    @Override
    public void process(FractalImage image) {
        double maxNormal = 0;

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Pixel current = image.pixel(x, y);
                if (current.hitCount() != 0) {
                    current.normal(Math.log10(current.hitCount()));
                    maxNormal = Math.max(maxNormal, current.normal());
                }
            }
        }
        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Pixel current = image.pixel(x, y);

                current.normal(current.normal() / maxNormal);

                current.red((int) (current.red() * Math.pow(current.normal(), EXPONENT)));
                current.green((int) (current.green() * Math.pow(current.normal(), EXPONENT)));
                current.blue((int) (current.blue() * Math.pow(current.normal(), EXPONENT)));
            }
        }
    }
}
