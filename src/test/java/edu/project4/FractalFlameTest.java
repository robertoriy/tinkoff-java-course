package edu.project4;

import edu.project4.model.ImageFormat;
import edu.project4.model.Rectangle;
import edu.project4.processor.GammaCorrection;
import edu.project4.processor.ImageProcessor;
import edu.project4.renderer.AdvancedRenderer;
import edu.project4.renderer.Renderer;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.specific.Cylinder;
import edu.project4.transformation.specific.Diamond;
import edu.project4.transformation.specific.Disc;
import edu.project4.transformation.specific.Ex;
import edu.project4.transformation.specific.Exponential;
import edu.project4.transformation.specific.FishEye;
import edu.project4.transformation.specific.Handkerchief;
import edu.project4.transformation.specific.Heart;
import edu.project4.transformation.specific.Hyperbolic;
import edu.project4.transformation.specific.Julia;
import edu.project4.transformation.specific.Polar;
import edu.project4.transformation.specific.Power;
import edu.project4.transformation.specific.Sinusoidal;
import edu.project4.transformation.specific.Spherical;
import edu.project4.transformation.specific.Swirl;
import edu.project4.transformation.specific.Tangent;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

final class FractalFlameTest {
    private static FractalFlame getExampleInstant() {
        Renderer renderer = new AdvancedRenderer();
        Rectangle world = new Rectangle(-4, -3, 8, 6);

        List<Transformation> transformations = new ArrayList<>();
        transformations.add(new Sinusoidal());
        transformations.add(new Spherical());
        transformations.add(new Swirl());
        transformations.add(new Ex());
        transformations.add(new FishEye());
        transformations.add(new Handkerchief());
        transformations.add(new Julia());
        transformations.add(new Disc());
        transformations.add(new Heart());
        transformations.add(new Polar());
        transformations.add(new Power());
        transformations.add(new Tangent());
        transformations.add(new Hyperbolic());
        transformations.add(new Diamond());
        transformations.add(new Cylinder());
        transformations.add(new Exponential());

        ImageProcessor processor = new GammaCorrection();

        return FractalFlame.create(1080, 1920, renderer, world, transformations, processor);
    }

    @Test
    @DisplayName("Тест на отсутствие ошибок")
    void generateNoExceptionsCase(@TempDir Path path) {
        Path testPath = path.resolve("test.png");
        FractalFlame fractal = getExampleInstant();

        assertThatCode(
            () -> fractal.generate(
                7, 1_000_000, 6, testPath, ImageFormat.JPEG
            )
        ).doesNotThrowAnyException();
        assertThat(testPath).exists();
    }
}
