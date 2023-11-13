package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public final class FridayTheThirteenth {
    private static final int DAY_THIRTEEN = 13;

    private FridayTheThirteenth() {
    }

    public static List<LocalDate> findAllInYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be positive");
        }
        List<LocalDate> requiredFridays = new ArrayList<>();
        LocalDate date = LocalDate.of(year, Month.JANUARY, DAY_THIRTEEN);

        while (date.getYear() == year) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                requiredFridays.add(date);
            }
            date = date.plusMonths(1);
        }
        return requiredFridays;
    }

    public static LocalDate getNext(LocalDate date) {
        return date.with(FridayTheThirteenth.findNext());
    }

    private static TemporalAdjuster findNext() {
        return temporal -> {
            LocalDate currentDate = LocalDate.from(temporal.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));
            while (currentDate.getDayOfMonth() != DAY_THIRTEEN) {
                currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            }
            return currentDate;
        };
    }
}
