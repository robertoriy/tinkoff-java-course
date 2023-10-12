package edu.hw1.task1;

public final class VideoLength {
    private static final int SECONDS_IN_MINUTE = 60;
    private static final String TIME_PATTERN = "\\d{2,9}:[0-5]\\d"; // mm:ss and minutes < Integer.MAX_VALUE
    private static final String SEPARATOR = ":";
    private static final int ERROR_RESPONSE = -1;

    private VideoLength() {

    }

    public static int minutesToSeconds(String time) {
        if (!isValidTime(time)) {
            return ERROR_RESPONSE;
        }

        String[] args = time.split(SEPARATOR);

        int minutes = Integer.parseInt(args[0]);
        int seconds = Integer.parseInt(args[1]);

        return minutes * SECONDS_IN_MINUTE + seconds;
    }

    private static boolean isValidTime(String time) {
        return (time != null && time.matches(TIME_PATTERN));
    }
}
