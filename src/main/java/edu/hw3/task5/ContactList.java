package edu.hw3.task5;

import java.util.Arrays;

public final class ContactList {
    private static final String NAME_PATTERN = "[a-zA-Z]+\\s?[a-zA-Z]*";

    private ContactList() {
    }

    public static Contact[] sortContacts(String[] names, Order order) {
        if (isNamesInvalid(names)) {
            return new Contact[0];
        }
        Contact[] contacts = parseContacts(names);
        Arrays.sort(contacts, Contact.getComparator(order));
        return contacts;
    }

    private static Contact[] parseContacts(String[] names) {
        Contact[] contacts = new Contact[names.length];

        for (int i = 0; i < names.length; i++) {
            String[] parts = names[i].split(" ");

            String firstName = parts[0];
            String lastName = (parts.length > 1) ? parts[1] : null;

            contacts[i] = new Contact(firstName, lastName);
        }

        return contacts;
    }

    private static boolean isNamesInvalid(String[] names) {
        if (names == null) {
            return true;
        }
        for (String name : names) {
            if (name == null || !name.matches(NAME_PATTERN)) {
                return true;
            }
        }
        return false;
    }
}
