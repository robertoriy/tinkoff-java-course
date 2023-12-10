package edu.project4.model;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class FractalImage {
    private final Pixel[] data;
    private final int height;
    private final int width;

    private FractalImage(Pixel[] data, int height, int width) {
        this.data = data;
        this.height = height;
        this.width = width;
    }

    public static FractalImage create(int height, int width) {
        Pixel[] data = new Pixel[height * width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                data[i * width + j] = Pixel.create(0, 0, 0, 0, 0);
            }
        }
        return new FractalImage(data, height, width);
    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < width
            && y >= 0 && y < height;
    }

    public Pixel pixel(int x, int y) {
        if (!contains(x, y)) {
            throw new NoSuchElementException();
        }
        return data[y * width + x];
    }

    public Pixel[] data() {
        return data;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FractalImage fractalImage = (FractalImage) o;
        return Arrays.equals(data, fractalImage.data())
            && height == fractalImage.height()
            && width == fractalImage.width();
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(data);
        result = 31 * result + Objects.hash(height);
        result = 31 * result + Objects.hash(width);
        return result;
    }

    @Override
    public String toString() {
        return "FractalImage{"
            + "data=" + Arrays.toString(data)
            + ", height =" + height
            + ", width =" + width + "}";
    }
}
