package edu.project4.renderer;

import edu.project4.model.AffineCoefficients;
import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Rectangle;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class SimpleRenderer extends AbstractRenderer {
    @Override
    public FractalImage render(
        FractalImage canvas,
        Rectangle world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        AffineCoefficients[] allCoefficients = randomAffineCoefficients(samples);
        for (int i = 0; i < samples; i++) {
            Point point = world.randomPoint();
            for (int j = DEFAULT_STEP_START; j < iterPerSample; j++) {
                int coefficientsIndex = ThreadLocalRandom.current().nextInt(0, allCoefficients.length);
                AffineCoefficients coefficients = allCoefficients[coefficientsIndex];
                point = affine(point, coefficients);

                int variationIndex = ThreadLocalRandom.current().nextInt(0, variations.size());
                Transformation transformation = variations.get(variationIndex);
                point = transformation.apply(point);

                if (j >= 0) {
                    double delta = 0;
                    for (int k = 0; k < symmetry; k++) {
                        delta += 2 * Math.PI / symmetry;
                        Point rotatedPoint = rotatePoint(point, delta);
                        if (!world.contains(rotatedPoint)) {
                            continue;
                        }
                        int x = (int) ((rotatedPoint.x() - world.x()) * canvas.width() / world.width());
                        int y = (int) ((rotatedPoint.y() - world.y()) * canvas.height() / world.height());
                        Pixel pixel = canvas.pixel(x, y);

                        setColor(pixel, coefficients.color());
                        pixel.hitCount(pixel.hitCount() + 1);
                    }
                }
            }
        }
        return canvas;
    }
}
