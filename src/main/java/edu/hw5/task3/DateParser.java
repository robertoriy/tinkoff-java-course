package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {
    private DateParser next;

    public static DateParser link(DateParser first, DateParser... parsers) {
        DateParser head = first;
        for (DateParser nextInChain : parsers) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract Optional<LocalDate> parseDate(String date);

    protected Optional<LocalDate> parseNext(String date) {
        if (next == null) {
            return Optional.empty();
        }
        return next.parseDate(date);
    }
}
