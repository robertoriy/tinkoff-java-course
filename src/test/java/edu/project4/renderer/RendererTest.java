package edu.project4.renderer;

import edu.project4.model.FractalImage;
import edu.project4.model.Rectangle;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

final class RendererTest {
    private static Stream<Arguments> provideRenderer() {
        return Stream.of(
            Arguments.of(new SimpleRenderer()),
            Arguments.of(new AdvancedRenderer())
        );
    }

    private static List<Transformation> getVariations() {
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
        return transformations;
    }

    @ParameterizedTest
    @DisplayName("Выполнение без ошибок")
    @MethodSource("provideRenderer")
    void render(Renderer renderer) {
        assertThatCode(
            () -> renderer.render(
                FractalImage.create(1000, 1000),
                new Rectangle(1, 1, 2, 2),
                getVariations(),
                5, 100_000, 10
            )
        ).doesNotThrowAnyException();
    }
}
