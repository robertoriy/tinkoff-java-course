package edu.project4.processor;

import edu.project4.model.FractalImage;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

final class ImageProcessorTest {
    private static Stream<Arguments> provideProcessor() {
        return Stream.of(
            Arguments.of(new GammaCorrection())
        );
    }

    @ParameterizedTest
    @DisplayName("Выполнение без ошибок")
    @MethodSource("provideProcessor")
    void render(ImageProcessor processor) {
        assertThatCode(
            () -> processor.process(FractalImage.create(1000, 1000))
        ).doesNotThrowAnyException();
    }
}
