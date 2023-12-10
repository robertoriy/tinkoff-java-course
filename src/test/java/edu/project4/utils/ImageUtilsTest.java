package edu.project4.utils;

import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

final class ImageUtilsTest {
    @Test
    @DisplayName("Сохранение файла")
    void saveNormalCase(@TempDir Path path) {
        Path actual = path.resolve("test.png");
        FractalImage fractalImage = FractalImage.create(10, 10);

        ImageUtils.save(fractalImage, actual, ImageFormat.PNG);

        assertThat(actual).exists();
    }
}
