package edu.hw1.task4;

public final class BrokenString {
    private BrokenString() {
    }

    public static String fixString(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Null input");
        }
        if (input.length() <= 1) {
            return input;
        }

        char[] charArray = input.toCharArray();

        for (int i = 0; i < charArray.length - 1; i += 2) {
            char temp = charArray[i];
            charArray[i] = charArray[i + 1];
            charArray[i + 1] = temp;
        }

        return String.valueOf(charArray);
    }
}
