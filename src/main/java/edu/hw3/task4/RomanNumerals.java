package edu.hw3.task4;

import java.util.TreeMap;

public final class RomanNumerals {
    private static final TreeMap<Integer, String> ROMAN_VALUES = new TreeMap<>();
    private static final int LIMIT_OF_ROMAN_NUMERALS = 4000;

    static {
        ROMAN_VALUES.put(1, "I");

        ROMAN_VALUES.put(4, "IV");
        ROMAN_VALUES.put(5, "V");
        ROMAN_VALUES.put(9, "IX");
        ROMAN_VALUES.put(10, "X");

        ROMAN_VALUES.put(40, "XL");
        ROMAN_VALUES.put(50, "L");
        ROMAN_VALUES.put(90, "XC");
        ROMAN_VALUES.put(100, "C");

        ROMAN_VALUES.put(400, "CD");
        ROMAN_VALUES.put(500, "D");
        ROMAN_VALUES.put(900, "CM");
        ROMAN_VALUES.put(1000, "M");
    }

    private RomanNumerals() {
    }

    public static String convertToRoman(int value) {
        if (value <= 0 || value >= LIMIT_OF_ROMAN_NUMERALS) {
            throw new IllegalArgumentException("Value must be in range [1, 3999]");
        }

        int currentArabicNumber = value;
        StringBuilder romanNumeral = new StringBuilder();

        while (currentArabicNumber > 0) {
            int closestValue = ROMAN_VALUES.floorKey(currentArabicNumber);
            String roman = ROMAN_VALUES.get(closestValue);
            romanNumeral.append(roman);
            currentArabicNumber -= closestValue;
        }

        return romanNumeral.toString();
    }
}
