package edu.hw5.task6;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Subsequences {
    private static final String ANY_CHARACTER_ZERO_OR_MORE_TIMES = ".*";

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
            patternBuilder.append(ANY_CHARACTER_ZERO_OR_MORE_TIMES).append(Pattern.quote(String.valueOf(symbol)));
        }
        patternBuilder.append(ANY_CHARACTER_ZERO_OR_MORE_TIMES);
        return patternBuilder.toString();
    }
}
