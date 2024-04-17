package edu.project4.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

final class RectangleTest {
    @Test
    @DisplayName("contains True Case")
    void containsTrueCase() {
        Rectangle rectangle = new Rectangle(0, 0, 20, 20);

        boolean actual = rectangle.contains(new Point(10, 10));

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("contains False Case")
    void containsFalseCase() {
        Rectangle rectangle = new Rectangle(0, 0, 20, 20);

        boolean actual = rectangle.contains(new Point(20, 20));

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("Получение случайной точки прямоугольника")
    void randomPoint() {
        Rectangle rectangle = new Rectangle(0, 0, 20, 20);

        Point random = rectangle.randomPoint();
        boolean actual = rectangle.contains(random);

        assertThat(actual).isTrue();
    }
}
