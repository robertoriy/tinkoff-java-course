package edu.hw2.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

final class ExprTest {
    @Nested
    class ConstantCase {
        @ParameterizedTest
        @DisplayName("Тест для констант")
        @CsvSource({
            "-12654, -12654",
            "0, 0",
            "1, 1",
            "1526, 1526",
            "-751.516, -751.516",
            "26.21, 26.21"
        })
        void testEvaluateConstantCase(double argument, double expected) {
            Expr.Constant constant = new Expr.Constant(argument);

            double actual = constant.evaluate();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class NegateCase {
        @ParameterizedTest
        @DisplayName("Тест для унарного минуса")
        @CsvSource({
            "-12654, 12654",
            "0, 0",
            "1, -1",
            "1526, -1526",
            "-751.516, 751.516",
            "26.21, -26.21"
        })
        void testEvaluateNegateConstantCase(double argument, double expected) {
            Expr.Negate negate = new Expr.Negate(new Expr.Constant(argument));

            double actual = negate.evaluate();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class AdditionCase {
        @ParameterizedTest
        @DisplayName("Тест для сложения")
        @CsvSource({
            "-12, 12, 0",
            "0, 7.5, 7.5",
            "13.56, 0, 13.56",
            "5.5, 4.5, 10",
        })
        void testEvaluateAdditionConstantCase(double first, double second, double expected) {
            Expr.Addition addition = new Expr.Addition(
                new Expr.Constant(first),
                new Expr.Constant(second)
            );

            double actual = addition.evaluate();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class MultiplicationCase {
        @ParameterizedTest
        @DisplayName("Тест для умножения")
        @CsvSource({
            "-12, 12, -144",
            "0, 7.5, 0",
            "13.56, 0, 0",
            "1, -7.5, -7.5",
            "13.56, 1, 13.56",
            "5.5, 4.5, 24.75",
        })
        void testEvaluateMultiplicationCase(double first, double second, double expected) {
            Expr.Multiplication multiplication = new Expr.Multiplication(
                new Expr.Constant(first),
                new Expr.Constant(second)
            );

            double actual = multiplication.evaluate();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class ExponentCase {
        @ParameterizedTest
        @DisplayName("Тест для возведения в степень")
        @CsvSource({
            "-12, 2, 144",
            "0, 7, 0",
            "13.56, 0, 1",
            "2, 8, 256",
            "9, 3, 729",
            "1, 34, 1",
            "432.342, 1, 432.342"
        })
        void testEvaluateExponentCase(double base, int power, double expected) {
            Expr.Exponent exponent = new Expr.Exponent(new Expr.Constant(base), power);

            double actual = exponent.evaluate();

            assertThat(actual).isEqualTo(expected);
        }
    }
}
