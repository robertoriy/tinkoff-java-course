package edu.project4.model;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

final class FractalImageTest {
    @Test
    @DisplayName("contains True Case")
    void containsTrueCase() {
        FractalImage fractalImage = FractalImage.create(20, 20);

        boolean actual = fractalImage.contains(10, 10);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("contains False Case")
    void containsFalseCase() {
        FractalImage fractalImage = FractalImage.create(20, 20);

        boolean actual = fractalImage.contains(20, 20);

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("Получение пикселя без исключения")
    void pixelNoExceptionsCase() {
        FractalImage fractalImage = FractalImage.create(20, 20);

        assertThatCode(() -> fractalImage.pixel(10, 10)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Исключение при попытке получить отсутствующий пиксель")
    void pixelExceptionsCase() {
        FractalImage fractalImage = FractalImage.create(20, 20);

        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> fractalImage.pixel(20, 20));
    }

    @Test
    @DisplayName("Создание картинки нужной размерности")
    void validData() {
        FractalImage fractalImage = FractalImage.create(20, 20);

        Pixel[] actual = fractalImage.data();

        assertThat(actual).hasSize(20 * 20);
    }
}
