package edu.project1.dictionary;

import java.util.Random;

public class HandwrittenDictionary implements Dictionary {
    private static final int MIN_WORD_LENGTH = 3;
    private final Random random = new Random();
    private final String[] words;

    public HandwrittenDictionary(String[] words) {
        if (isInputInvalid(words)) {
            throw new IllegalArgumentException("Bad input: words must be not null and have length > 2");
        }
        this.words = words;
    }

    @Override
    public String getRandomWord() {
        int index = random.nextInt(words.length);
        return words[index];
    }

    private boolean isInputInvalid(String[] words) {
        if (words == null) {
            return true;
        }
        for (String word : words) {
            if (word == null || word.length() < MIN_WORD_LENGTH) {
                return true;
            }
        }
        return false;
    }
}
