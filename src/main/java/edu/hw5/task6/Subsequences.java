package edu.hw5.task6;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Subsequences {
    private Subsequences() {
    }

    public static boolean check(String subsequence, String fullLine) {
        Objects.requireNonNull(subsequence, "'subsequence' is expected to be non-null");
        Objects.requireNonNull(fullLine, "'fullLine' is expected to be non-null");

        return Pattern.compile(createPattern(subsequence)).matcher(fullLine).matches();
    }

    private static String createPattern(String subsequence) {
        StringBuilder patternBuilder = new StringBuilder();
        for (char symbol : subsequence.toCharArray()) {
            patternBuilder.append(".*").append(Pattern.quote(String.valueOf(symbol)));
        }
        patternBuilder.append(".*");
        return patternBuilder.toString();
    }
}
