package edu.hw5.task3.parser;

import edu.hw5.task3.DateParser;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public final class WordDateParser extends DateParser {
    @Override
    public Optional<LocalDate> parseDate(String date) {
        Objects.requireNonNull(date, "'date' is expected to be non-null");

        LocalDate currentDate = LocalDate.now();

        return switch (date) {
            case "tomorrow" -> Optional.of(currentDate.plusDays(1));
            case "today" -> Optional.of(currentDate);
            case "yesterday" -> Optional.of(currentDate.minusDays(1));
            default -> parseNext(date);
        };
    }
}
