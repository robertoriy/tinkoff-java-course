package edu.hw3.task6;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

final class StockTest {
    @ParameterizedTest
    @DisplayName("Тест на корректную сортировку по цене")
    @MethodSource("provideStocks")
    void testSortingByLastName(Stock[] actual, Stock[] expected) {
        Arrays.sort(actual);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideStocks() {
        return Stream.of(
            Arguments.of(
                new Stock[] {
                    new Stock("a", 15),
                    new Stock("b", 130),
                    new Stock("c", 1),
                    new Stock("d", 112),
                    new Stock("e", 12)
                },
                new Stock[] {
                    new Stock("c", 1),
                    new Stock("e", 12),
                    new Stock("a", 15),
                    new Stock("d", 112),
                    new Stock("b", 130)
                }
            )
        );
    }
}
