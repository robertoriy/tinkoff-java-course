package edu.project4.utils;

import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import edu.project4.model.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private ImageUtils() {
    }

    public static void save(FractalImage image, Path fileName, ImageFormat format) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Pixel pixel = image.pixel(x, y);
                Color color = new Color(pixel.red(), pixel.green(), pixel.blue());
                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }
        try {
            ImageIO.write(bufferedImage, format.name(), fileName.toFile());
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }
}
