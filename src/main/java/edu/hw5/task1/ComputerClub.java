package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ComputerClub {
    private static final Pattern VISIT_RECORD_PATTERN =
        Pattern.compile("(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    private ComputerClub() {
    }

    public static String getAverageTime(List<String> timeRanges) {
        if (timeRanges == null || timeRanges.isEmpty()) {
            throw new IllegalArgumentException("Time ranges must be not null");
        }
        Duration averageDuration = getAverageDuration(timeRanges);
        return String.format("%dч %dм", averageDuration.toHours(), averageDuration.toMinutesPart());
    }

    private static Duration getAverageDuration(List<String> timeRanges) {
        return timeRanges.stream()
            .map(ComputerClub::parseDuration)
            .reduce(Duration::plus)
            .map(totalDuration -> totalDuration.dividedBy(timeRanges.size()))
            .orElse(Duration.ZERO);
    }

    private static Duration parseDuration(String timeRange) {
        if (timeRange == null) {
            throw new IllegalArgumentException("Time range must be not null");
        }
        Matcher visitMatcher = VISIT_RECORD_PATTERN.matcher(timeRange);
        if (invalidTimeRange(visitMatcher)) {
            throw new IllegalArgumentException("Wrong pattern of time range for '" + timeRange + "'");
        }
        return computeDuration(visitMatcher.group(1), visitMatcher.group(2));
    }

    private static boolean invalidTimeRange(Matcher visitMatcher) {
        return !visitMatcher.matches();
    }

    private static Duration computeDuration(String firstDate, String secondDate) {
        LocalDateTime fromDateTime = LocalDateTime.parse(firstDate, DATE_FORMATTER);
        LocalDateTime toDateTime = LocalDateTime.parse(secondDate, DATE_FORMATTER);

        Duration duration = Duration.between(fromDateTime, toDateTime);
        if (duration.isNegative()) {
            throw new IllegalArgumentException("Wrong order of time range");
        }
        return duration;
    }
}
