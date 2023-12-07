package edu.hw8.task1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class QuoteDictionary {
    private static final Map<String, String> QUOTES = new HashMap<>();
    private static final String DEFAULT_MESSAGE =
        "There are no quotes associated with this topic / Нет цитат, связанных с этой темой.";

    private QuoteDictionary() {
    }

    static {
        QUOTES.put(
            "личности",
            "Не переходи на личности там, где их нет."
        );
        QUOTES.put(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами."
        );
        QUOTES.put(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        );
        QUOTES.put(
            "интеллект",
            "Чем ниже интеллект, тем громче оскорбления."
        );
    }

    public static String getQuote(String topic) {
        Objects.requireNonNull(topic);
        return QUOTES.computeIfAbsent(topic.toLowerCase(), key -> DEFAULT_MESSAGE);
    }
}
