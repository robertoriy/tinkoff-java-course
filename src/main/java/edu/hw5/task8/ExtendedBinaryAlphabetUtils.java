package edu.hw5.task8;

import java.util.Objects;

public final class ExtendedBinaryAlphabetUtils {
    private static final String NULL_ERROR_MESSAGE = "'input' is expected to be non-null";

    private ExtendedBinaryAlphabetUtils() {
    }

    public static boolean hasOddLength(String input) {
        Objects.requireNonNull(input, NULL_ERROR_MESSAGE);
        return input.matches("[01]([01][01])*");
    }

    // начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    public static boolean startsZeroAndOddOrStartsOneAndEven(String input) {
        Objects.requireNonNull(input, NULL_ERROR_MESSAGE);
        return input.matches("(0([01][01])*|1[01]([01][01])*)");
    }

    // количество нулей кратно 3
    public static boolean numberOfZerosIsMultipleOfThree(String input) {
        Objects.requireNonNull(input, NULL_ERROR_MESSAGE);
        return input.matches("1*(1*01*01*0)*");
    }

    // каждый нечетный символ равен 1
    public static boolean isEveryOddSymbolOne(String input) {
        Objects.requireNonNull(input, NULL_ERROR_MESSAGE);
        return input.matches("(1[01])*1?");
    }
}
