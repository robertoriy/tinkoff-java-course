package edu.hw1.task5;

public final class SpecialPalindrome {
    private SpecialPalindrome() {

    }

    public static boolean isPalindromeDescendant(int number) {
        if (number < 0) {
            return false;
        }
        char[] digits = Integer.toString(number).toCharArray();

        if (isPalindrome(digits)) {
            return true;
        }

        while (digits.length > 2) {

            if (digits.length % 2 != 0) {
                return false;
            }
            digits = sumByPairs(digits);

            if (isPalindrome(digits)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPalindrome(char[] data) {
        for (int i = 0, j = data.length - 1; i < j; i++, j--) {
            if (data[i] != data[j]) {
                return false;
            }
        }
        return true;
    }

    private static char[] sumByPairs(char[] digits) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < digits.length - 1; i += 2) {
            int first = Character.getNumericValue(digits[i]);
            int second = Character.getNumericValue(digits[i + 1]);

            result.append(first + second);
        }
        return result.toString().toCharArray();
    }

}
