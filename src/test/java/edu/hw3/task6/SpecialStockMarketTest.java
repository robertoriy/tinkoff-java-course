package edu.hw3.task6;

import java.util.NoSuchElementException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class SpecialStockMarketTest {
    @ParameterizedTest
    @DisplayName("Тест на самую ценную акцию")
    @MethodSource("provideStocks")
    void testMostValuableStock(Stock[] stocks, Stock expectedMostValuable) {
        StockMarket stockMarket = new SpecialStockMarket();
        for (Stock stock : stocks) {
            stockMarket.add(stock);
        }

        Stock actualMostValuable = stockMarket.mostValuableStock();

        assertThat(actualMostValuable).isEqualTo(expectedMostValuable);
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
                new Stock("b", 130)
            )
        );
    }

    @Test
    @DisplayName("Тест на исключение при некорректном вводе")
    void testAddRemoveWithNull() {
        StockMarket stockMarket = new SpecialStockMarket();

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> stockMarket.add(null));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> stockMarket.remove(null));
    }

    @Test
    @DisplayName("Тест на исключение при пустом содержимом")
    void testMostValuableWithEmptyStocks() {
        StockMarket stockMarket = new SpecialStockMarket();

        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(stockMarket::mostValuableStock);
    }
}
