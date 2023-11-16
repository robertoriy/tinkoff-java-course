package edu.hw5.task3.parser;

import edu.hw5.task3.DateParser;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DashSeparatedDateParser extends DateParser {
    private static final Pattern PATTERN = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{1,2})");

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
        return LocalDate.of(
            Integer.parseInt(matcher.group(1)),
            Integer.parseInt(matcher.group(2)),
            Integer.parseInt(matcher.group(3))
        );
    }
}
