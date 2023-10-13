package edu.hw1.task1;

public final class VideoLength {
    private static final int SECONDS_IN_MINUTE = 60;
    private static final String TIME_PATTERN = "\\d{2,}:[0-5]\\d"; // mm:ss format
    private static final String SEPARATOR = ":";
    private static final int ERROR_RESPONSE = -1;

    private VideoLength() {

    }

    public static int minutesToSeconds(String time) {
        if (!isValidInput(time)) {
            return ERROR_RESPONSE;
        }
        String[] args = time.split(SEPARATOR);

        int totalSeconds;
        try {
            int minutes = Integer.parseInt(args[0]);
            int seconds = Integer.parseInt(args[1]);
            totalSeconds = Math.addExact(Math.multiplyExact(minutes, SECONDS_IN_MINUTE), seconds);
        } catch (NumberFormatException | ArithmeticException e) {
            return ERROR_RESPONSE;
        }

        return totalSeconds;
    }

    private static boolean isValidInput(String time) {
        return (time != null && time.matches(TIME_PATTERN));
    }
}
