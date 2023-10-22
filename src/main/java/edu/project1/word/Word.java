package edu.project1.word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Word {
    private static final int MIN_WORD_LENGTH = 3;
    private final String word;
    private final Map<Character, List<Integer>> letterIndexes = new HashMap<>();

    public Word(String word) {
        if (isInputInvalid(word)) {
            throw new IllegalArgumentException("A word must be not null and contain only letters, at least 3");
        }
        this.word = word;
        fillLetterIndexes(word);
    }

    public List<Integer> getLetterIndexes(char letter) {
        return letterIndexes.get(letter);
    }

    public boolean contain(char c) {
        return letterIndexes.containsKey(c);
    }

    public int getLength() {
        return word.length();
    }

    private boolean isInputInvalid(String word) {
        return (word == null || word.length() < MIN_WORD_LENGTH);
    }

    private void fillLetterIndexes(String word) {
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);

            letterIndexes.computeIfAbsent(letter, k -> new ArrayList<>());

            letterIndexes.get(letter).add(i);
        }
    }
}
