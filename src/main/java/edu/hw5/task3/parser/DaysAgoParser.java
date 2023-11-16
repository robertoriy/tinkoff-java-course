package edu.hw5.task3.parser;

import edu.hw5.task3.DateParser;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DaysAgoParser extends DateParser {
    private static final Pattern PATTERN = Pattern.compile("(\\d+) days ago");

    @Override
    public Optional<LocalDate> parseDate(String date) {
        Objects.requireNonNull(date, "'date' is expected to be non-null");

        if (date.equals("1 day ago")) {
            return getDateMinus(1);
        }

        Matcher matcher = PATTERN.matcher(date);

        if (!matcher.matches()) {
            return parseNext(date);
        }
        int requiredDays = Integer.parseInt(matcher.group(1));
        return getDateMinus(requiredDays);
    }

    private Optional<LocalDate> getDateMinus(int days) {
        return Optional.of(LocalDate.now().minusDays(days));
    }
}
