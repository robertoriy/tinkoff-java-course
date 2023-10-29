package edu.hw3.task4;

public enum Numeral {
    ONE(1, "I"), FOUR(4, "IV"), FIVE(5, "V"), NINE(9, "IX"), TEN(10, "X"),
    FORTY(40, "XL"), FIFTY(50, "L"), NINETY(90, "XC"), ONE_HUNDRED(100, "C"),
    FOUR_HUNDRED(400, "CD"), FIVE_HUNDRED(500, "D"), NINE_HUNDRED(900, "CM"), THOUSAND(1000, "M");

    private final int arabic;
    private final String roman;

    Numeral(int arabic, String roman) {
        this.arabic = arabic;
        this.roman = roman;
    }

    public final int arabic() {
        return arabic;
    }

    public final String roman() {
        return roman;
    }
}
