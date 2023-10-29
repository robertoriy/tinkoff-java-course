package edu.hw3.task5;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

final class ContactListTest {
    @ParameterizedTest
    @DisplayName("Тест на корректную работу")
    @MethodSource("provideNamesAndContacts")
    void testParseContacts(String[] inputNames, Order order, Contact[] expected) {
        Contact[] actual = ContactList.sortContacts(inputNames, order);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideNamesAndContacts() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                Order.ASCENDING,
                new Contact[] {
                    new Contact("Thomas", "Aquinas"),
                    new Contact("Rene", "Descartes"),
                    new Contact("David", "Hume"),
                    new Contact("John", "Locke")
                }
            ),
            Arguments.of(
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
                Order.DESCENDING,
                new Contact[] {
                    new Contact("Carl", "Gauss"),
                    new Contact("Leonhard", "Euler"),
                    new Contact("Paul", "Erdos")
                }
            ),
            Arguments.of(
                new String[] {"ABB", "Cat Zsd", "DSF AAA"},
                Order.ASCENDING,
                new Contact[] {
                    new Contact("DSF", "AAA"),
                    new Contact("ABB", null),
                    new Contact("Cat", "Zsd")
                }
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Тест на возврат пустого массива из-за некорректного ввода")
    @MethodSource("provideInvalidInputCase")
    void testClusterizeInvalidInputCase(String[] inputNames) {
        Contact[] actual = ContactList.sortContacts(inputNames, Order.ASCENDING);

        assertThat(actual).isEqualTo(new Contact[0]);
    }

    private static Stream<Arguments> provideInvalidInputCase() {
        return Stream.of(
            Arguments.of((Object) null),
            Arguments.of((Object) new String[] {"John Locke", ""}),
            Arguments.of((Object) new String[] {"John Locke", "    "}),
            Arguments.of((Object) new String[] {"John Locke", "  fsdg"}),
            Arguments.of((Object) new String[] {"John Locke", "aaa6 5df"}),
            Arguments.of((Object) new String[] {"John Locke", "asdasd asdasd asdasd"})
        );
    }
}
