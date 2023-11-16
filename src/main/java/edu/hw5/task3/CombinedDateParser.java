package edu.hw5.task3;

import edu.hw5.task3.parser.DashSeparatedDateParser;
import edu.hw5.task3.parser.DaysAgoParser;
import edu.hw5.task3.parser.SlashSeparatedDateParser;
import edu.hw5.task3.parser.WordDateParser;
import java.time.LocalDate;
import java.util.Optional;

public final class CombinedDateParser {
    private CombinedDateParser() {
    }

    public static Optional<LocalDate> parseDate(String date) {
        DateParser parser = DateParser.link(
            new DaysAgoParser(),
            new WordDateParser(),
            new DashSeparatedDateParser(),
            new SlashSeparatedDateParser()
        );
        return parser.parseDate(date);
    }
}
