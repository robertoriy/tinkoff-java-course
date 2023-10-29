package edu.hw3.task1;

public final class AtbashCipher {
    private AtbashCipher() {
    }

    public static String atbash(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input must be not null and not empty");
        }
        StringBuilder encrypted = new StringBuilder();

        for (char symbol : input.toCharArray()) {
            if (isLatinLetter(symbol)) {
                encrypted.append(encrypt(symbol));
            } else {
                encrypted.append(symbol);
            }
        }
        return encrypted.toString();
    }

    private static boolean isLatinLetter(char symbol) {
        return (symbol >= 'A' && symbol <= 'Z') || (symbol >= 'a' && symbol <= 'z');
    }

    private static char encrypt(char symbol) {
        char base = Character.isUpperCase(symbol) ? 'A' : 'a';
        return (char) (base + ('z' - Character.toLowerCase(symbol)));
    }
}
