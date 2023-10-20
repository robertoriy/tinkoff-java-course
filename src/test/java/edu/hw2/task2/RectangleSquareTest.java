package edu.hw2.task2;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.offset;

final class RectangleSquareTest {
    private static final double DELTA = 1e-10;

    @Nested
    class Area {
        @ParameterizedTest
        @DisplayName("Тест на корректную площадь после создания объекта")
        @MethodSource("provideTestCasesForArea")
        void testAreaNormalCase(Rectangle rectangle, double expected) {
            double actualArea = rectangle.area();

            assertThat(actualArea).isEqualTo(expected, offset(DELTA));
        }

        private static Stream<Arguments> provideTestCasesForArea() {
            return Stream.of(
                Arguments.of(new Rectangle(), 0),
                Arguments.of(new Square(), 0),
                Arguments.of(new Rectangle(10, 20), 200),
                Arguments.of(new Square(10), 100),
                Arguments.of(new Rectangle(5, 7), 35),
                Arguments.of(new Square(0.4), 0.16),
                Arguments.of(new Rectangle(12, 10), 120),
                Arguments.of(new Square(0.07), 0.0049)
            );
        }

        @ParameterizedTest
        @DisplayName("Тест на корректную площадь после изменения сторон")
        @MethodSource("provideTestCasesForSetter")
        void testAreaSetterCase(Rectangle rectangle, double height, double width, double expected) {
            double actualArea = rectangle.setHeight(height).setWidth(width).area();

            assertThat(actualArea).isEqualTo(expected, offset(DELTA));
        }

        private static Stream<Arguments> provideTestCasesForSetter() {
            return Stream.of(
                Arguments.of(new Rectangle(), 10, 20, 200),
                Arguments.of(new Square(), 10, 20, 200),
                Arguments.of(new Rectangle(), 0.05, 0.3, 0.015),
                Arguments.of(new Square(), 0.05, 0.3, 0.015)
            );
        }
    }

    @Nested
    class InvalidInput {
        @ParameterizedTest
        @DisplayName("Тест на некорректные входные данные конструктора прямоугольника")
        @CsvSource({
            "-12, 124",
            "1.054654, -1",
            "-0.6, -1.26",
        })
        void testConstructorRectangleCase(double height, double width) {
            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Rectangle(height, width));
        }

        @ParameterizedTest
        @DisplayName("Тест на некорректные входные данные конструктора квадрата")
        @ValueSource(doubles = {-1, -2, -0.001, -0.000201})
        void testConstructorSquareCase(double side) {
            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Square(side));
        }

        @ParameterizedTest
        @DisplayName("Тест на некорректные входные данные для изменения сторон прямоугольника")
        @ValueSource(doubles = {-1, -2, -0.001, -0.000201})
        void testSetterRectangleCase(double argument) {
            Rectangle rectangle = new Rectangle();

            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> rectangle.setHeight(argument));
            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> rectangle.setWidth(argument));
        }

        @ParameterizedTest
        @DisplayName("Тест на некорректные входные данные для изменения сторон квадрата")
        @ValueSource(doubles = {-1, -2, -0.001, -0.000201})
        void testSetterSquareCase(double argument) {
            Square square = new Square();

            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> square.setSide(argument));
            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> square.setHeight(argument));
            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> square.setWidth(argument));
        }
    }
}
