package edu.hw3.task5;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

final class ContactTest {
    @ParameterizedTest
    @DisplayName("Тест на корректную работу")
    @MethodSource("provideContacts")
    void testSortingByLastName(Contact[] actual, Order order, Contact[] expected) {
        Arrays.sort(actual, Contact.getComparator(order));

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideContacts() {
        return Stream.of(
            Arguments.of(
                new Contact[] {
                    new Contact("ABA", null),
                    new Contact("Carl", "AA"),
                    new Contact("AAAA", "ZZ"),
                    new Contact("ZX", null)
                },
                Order.ASCENDING,
                new Contact[] {
                    new Contact("Carl", "AA"),
                    new Contact("ABA", null),
                    new Contact("ZX", null),
                    new Contact("AAAA", "ZZ")
                }
            ),
            Arguments.of(
                new Contact[] {
                    new Contact("ABA", null),
                    new Contact("Carl", "AA"),
                    new Contact("AAAA", "ZZ"),
                    new Contact("ZX", null)
                },
                Order.DESCENDING,
                new Contact[] {
                    new Contact("AAAA", "ZZ"),
                    new Contact("ZX", null),
                    new Contact("ABA", null),
                    new Contact("Carl", "AA")
                }
            )
        );
    }
}
