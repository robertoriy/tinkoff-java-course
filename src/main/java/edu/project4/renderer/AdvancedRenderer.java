package edu.project4.renderer;

import edu.project4.model.AffineCoefficients;
import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Rectangle;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class AdvancedRenderer extends AbstractRenderer {
    private static final int DEFAULT_POOL_SIZE = 8;
    private final ExecutorService executor = Executors.newFixedThreadPool(DEFAULT_POOL_SIZE);

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rectangle world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        AffineCoefficients[] coefficients = randomAffineCoefficients(samples);
        for (int i = 0; i < samples; i++) {
            executor.execute(() -> iterations(
                canvas,
                world,
                iterPerSample,
                symmetry,
                variations,
                coefficients
            ));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        return canvas;
    }

    private void iterations(
        FractalImage canvas,
        Rectangle world,
        int iterPerSample,
        int symmetry,
        List<Transformation> variations,
        AffineCoefficients[] allCoefficients
    ) {
        Point point = world.randomPoint();
        for (int i = DEFAULT_STEP_START; i < iterPerSample; i++) {
            int coefficientsIndex = ThreadLocalRandom.current().nextInt(0, allCoefficients.length);
            AffineCoefficients coefficients = allCoefficients[coefficientsIndex];
            point = affine(point, coefficients);

            int variationIndex = ThreadLocalRandom.current().nextInt(0, variations.size());
            Transformation transformation = variations.get(variationIndex);
            point = transformation.apply(point);

            if (i >= 0) {
                double delta = 0;
                for (int j = 0; j < symmetry; j++) {
                    delta += 2 * Math.PI / symmetry;
                    Point rotatedPoint = rotatePoint(point, delta);
                    if (!world.contains(rotatedPoint)) {
                        continue;
                    }
                    int x = (int) ((rotatedPoint.x() - world.x()) * canvas.width() / world.width());
                    int y = (int) ((rotatedPoint.y() - world.y()) * canvas.height() / world.height());
                    Pixel pixel = canvas.pixel(x, y);

                    synchronized (pixel) {
                        setColor(pixel, coefficients.color());
                        pixel.hitCount(pixel.hitCount() + 1);
                    }
                }
            }
        }
    }
}
