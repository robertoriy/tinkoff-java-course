package edu.hw1.task2;

public final class NumberOfDigits {
    private static final int DIVIDER = 10;

    private NumberOfDigits() {
    }

    public static int countDigits(int number) {
        int temp;
        /*
        Для числа равного Integer.MIN_VALUE используется замена Integer.MAX_VALUE, так как
        Math.absExact(Integer.MIN_VALUE) throws ArithmeticException
        Данная замена не влияет на количество цифр
         */
        try {
            temp = Math.absExact(number);
        } catch (ArithmeticException e) {
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
