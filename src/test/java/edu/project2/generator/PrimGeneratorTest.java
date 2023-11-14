package edu.project2.generator;

import edu.project2.model.Maze;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class PrimGeneratorTest {
    @ParameterizedTest
    @DisplayName("Тест на размерность лабиринта")
    @CsvSource({
        "5, 5",
        "2, 4",
        "10, 3",
        "3, 3"
    })
    void shouldGenerateMaze(int height, int width) {
        Generator generator = new PrimGenerator();

        Maze actual = generator.generate(height, width);

        assertThat(actual.getGrid()).hasDimensions(height, width);
    }

    @ParameterizedTest
    @DisplayName("Тест на ввод некорректных данных")
    @CsvSource({
        "-1, 2",
        "0, 4",
        "3, -3",
        "4, 0",
        "0, 0"
    })
    void shouldThrowIllegalArgumentException(int height, int width) {
        Generator generator = new PrimGenerator();

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> generator.generate(height, width));
    }
}
