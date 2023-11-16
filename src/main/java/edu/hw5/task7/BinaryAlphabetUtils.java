package edu.hw5.task7;

import java.util.Objects;

public final class BinaryAlphabetUtils {
    private static final String NULL_ERROR_MESSAGE = "'input' is expected to be non-null";

    private BinaryAlphabetUtils() {
    }

    // содержит не менее 3 символов, причем третий символ равен 0
    public static boolean containAtLeastThreeSymbolsAndThirdIsZero(String input) {
        Objects.requireNonNull(input, NULL_ERROR_MESSAGE);
        return input.matches("[01]{2}0[01]*");
    }

    public static boolean startsAndEndsWithTheSameSymbol(String input) {
        Objects.requireNonNull(input, NULL_ERROR_MESSAGE);
        return input.matches("([01]|0[01]*0|1[01]*1)");
    }

    // длина не менее 1 и не более 3
    public static boolean hasLengthNotMoreThanThree(String input) {
        Objects.requireNonNull(input, NULL_ERROR_MESSAGE);
        return input.matches("[01]{1,3}");
    }
}
