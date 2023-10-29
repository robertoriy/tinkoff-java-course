package edu.hw3.task5;

import java.util.Comparator;

public record Contact(String firstName, String lastName) {
    public static Comparator<Contact> getComparator(Order order) {
        return switch (order) {
            case ASCENDING -> Comparator.comparing(
                (Contact contact) -> contact.lastName() == null ? contact.firstName() : contact.lastName()
            );
            case DESCENDING -> Comparator.comparing(
                (Contact contact) -> contact.lastName() == null ? contact.firstName() : contact.lastName(),
                Comparator.reverseOrder()
            );
        };
    }
}
