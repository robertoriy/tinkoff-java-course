package edu.hw5.task3.parser;

import edu.hw5.task3.DateParser;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SlashSeparatedDateParser extends DateParser {
    private static final Pattern PATTERN = Pattern.compile("(\\d{1,2})/(\\d{1,2})/(\\d{2}|\\d{4})");
    private static final int DAYS_IN_CENTURY = 100;

    @Override
    public Optional<LocalDate> parseDate(String date) {
        Objects.requireNonNull(date, "'date' is expected to be non-null");

        Matcher matcher = PATTERN.matcher(date);

        if (!matcher.matches()) {
            return parseNext(date);
        }

        return Optional.of(getParsed(matcher));
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private LocalDate getParsed(Matcher matcher) {
        int correctYear = Integer.parseInt(matcher.group(3));
        if (correctYear < DAYS_IN_CENTURY) {
            correctYear += LocalDate.now().getYear() / DAYS_IN_CENTURY * DAYS_IN_CENTURY;
        }
        return LocalDate.of(
            correctYear,
            Integer.parseInt(matcher.group(2)),
            Integer.parseInt(matcher.group(1))
        );
    }
}
