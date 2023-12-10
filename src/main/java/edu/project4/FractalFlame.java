package edu.project4;

import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import edu.project4.model.Rectangle;
import edu.project4.processor.ImageProcessor;
import edu.project4.renderer.Renderer;
import edu.project4.transformation.Transformation;
import edu.project4.utils.ImageUtils;
import java.nio.file.Path;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FractalFlame {
    private static final Logger LOGGER = LogManager.getLogger();
    private final FractalImage fractalImage;
    private final Renderer renderer;
    private final Rectangle world;
    private final List<Transformation> variations;
    private final ImageProcessor processor;

    private FractalFlame(
        int height,
        int width,
        Renderer renderer,
        Rectangle world,
        List<Transformation> variations,
        ImageProcessor processor
    ) {
        this.fractalImage = FractalImage.create(height, width);
        this.renderer = renderer;
        this.world = world;
        this.variations = variations;
        this.processor = processor;
    }

    public static FractalFlame create(
        int height,
        int width,
        Renderer renderer,
        Rectangle world,
        List<Transformation> variations,
        ImageProcessor processor
    ) {
        return new FractalFlame(height, width, renderer, world, variations, processor);
    }

    public void generate(int samples, int iterPerSample, int symmetry, Path path, ImageFormat format) {
        LOGGER.info("rendering...");
        renderer.render(fractalImage, world, variations, samples, iterPerSample, symmetry);

        LOGGER.info("processing...");
        processor.process(fractalImage);

        LOGGER.info("saving...");
        ImageUtils.save(fractalImage, path, format);
    }
}
