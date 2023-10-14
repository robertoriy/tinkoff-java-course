package edu.hw1.task6;

import java.util.Arrays;

public final class Kaprekar {
    private static final int KAPREKAR_CONSTANT = 6174;
    private static final int[] REPDIGITS = {
        1111, 2222, 3333,
        4444, 5555, 6666,
        7777, 8888, 9999
    };
    private static final int MIN_VALID_VALUE = 1001;
    private static final int MAX_VALID_VALUE = 9999;

    private Kaprekar() {

    }

    public static int countK(int number) {
        if (!isValidInput(number)) {
            throw new IllegalArgumentException("Must be in range [1001, 9999] and not be repdigit");
        }
        return routine(number);
    }

    private static boolean isValidInput(int number) {
        return (number >= MIN_VALID_VALUE && number <= MAX_VALID_VALUE && !isRepdigit(number));
    }

    private static boolean isRepdigit(int number) {
        for (int repdigit : REPDIGITS) {
            if (number == repdigit) {
                return true;
            }
        }
        return false;
    }

    private static int routine(int number) {
        if (number == KAPREKAR_CONSTANT) {
            return 0;
        }
        char[] digits = prepareArgument(number);

        int min = Integer.parseInt(String.valueOf(digits));
        reverseOrder(digits);
        int max = Integer.parseInt(String.valueOf(digits));

        return routine(max - min) + 1;
    }

    private static char[] prepareArgument(int number) {
        char[] digits = String.format("%04d", number).toCharArray();
        Arrays.sort(digits);
        return digits;
    }

    private static void reverseOrder(char[] array) {
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            char tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
        }
    }
}
