package edu.hw1.task2;

public final class NumberOfDigits {
    private static final int DIVIDER = 10;

    private NumberOfDigits() {
    }

    public static int countDigits(int number) {
        int temp = Math.abs(number);
        /*
        Случай, который ломает логику программы, так как
        Math.abs(Integer.MIN_VALUE) == Integer.MIN_VALUE
        используется Integer.MAX_VALUE, так как такое же количество цифр
         */
        if (temp == Integer.MIN_VALUE) {
            temp = Integer.MAX_VALUE;
        }
        int count = 0;

        do {
            temp /= DIVIDER;
            ++count;
        } while (temp > 0);

        return count;
    }
}
