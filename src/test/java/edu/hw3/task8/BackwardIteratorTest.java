package edu.hw3.task8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

final class BackwardIteratorTest {
    @ParameterizedTest
    @DisplayName("Тест итератора на обратный порядок")
    @MethodSource("provideListsInOppositeOrders")
    <T> void testIteratorNormalCase(List<T> list, List<T> expectedOrder) {
        Iterator<T> iterator = new BackwardIterator<>(list);
        List<T> actualOrder = new ArrayList<>();

        while (iterator.hasNext()) {
            actualOrder.add(iterator.next());
        }

        assertThat(actualOrder).isEqualTo(expectedOrder);
    }

    private static Stream<Arguments> provideListsInOppositeOrders() {
        return Stream.of(
            Arguments.of(List.of(1, 5, 3, 4), List.of(4, 3, 5, 1)),
            Arguments.of(List.of("a", "b", "x", "d"), List.of("d", "x", "b", "a"))
        );
    }

    @Test
    @DisplayName("Тест итератора на исключение при передаче null")
    void testIteratorInvalidInput() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new BackwardIterator<Integer>(null));
    }
}
