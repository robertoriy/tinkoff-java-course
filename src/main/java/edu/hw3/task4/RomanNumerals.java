package edu.hw3.task4;

import java.util.NavigableMap;
import java.util.TreeMap;

public final class RomanNumerals {
    private static final NavigableMap<Integer, String> ROMAN_VALUES = new TreeMap<>();
    private static final int LIMIT_OF_ROMAN_NUMERALS = 4000;

    static {
        ROMAN_VALUES.put(Numeral.ONE.arabic(), Numeral.ONE.roman());

        ROMAN_VALUES.put(Numeral.FOUR.arabic(), Numeral.FOUR.roman());
        ROMAN_VALUES.put(Numeral.FIVE.arabic(), Numeral.FIVE.roman());
        ROMAN_VALUES.put(Numeral.NINE.arabic(), Numeral.NINE.roman());
        ROMAN_VALUES.put(Numeral.TEN.arabic(), Numeral.TEN.roman());

        ROMAN_VALUES.put(Numeral.FORTY.arabic(), Numeral.FORTY.roman());
        ROMAN_VALUES.put(Numeral.FIFTY.arabic(), Numeral.FIFTY.roman());
        ROMAN_VALUES.put(Numeral.NINETY.arabic(), Numeral.NINETY.roman());
        ROMAN_VALUES.put(Numeral.ONE_HUNDRED.arabic(), Numeral.ONE_HUNDRED.roman());

        ROMAN_VALUES.put(Numeral.FOUR_HUNDRED.arabic(), Numeral.FOUR_HUNDRED.roman());
        ROMAN_VALUES.put(Numeral.FIVE_HUNDRED.arabic(), Numeral.FIVE_HUNDRED.roman());
        ROMAN_VALUES.put(Numeral.NINE_HUNDRED.arabic(), Numeral.NINE_HUNDRED.roman());
        ROMAN_VALUES.put(Numeral.THOUSAND.arabic(), Numeral.THOUSAND.roman());
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
