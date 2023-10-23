package edu.project1.controller;

import edu.project1.guess.Defeat;
import edu.project1.guess.GuessState;
import edu.project1.guess.Hit;
import edu.project1.guess.Miss;
import edu.project1.guess.Victory;
import edu.project1.word.Word;
import java.util.Arrays;
import java.util.List;

public class Session {
    private static final int DEFAULT_MAX_ALLOWED_MISSES = 5;
    private final Word answer;
    private final char[] hint;
    private final int maxAllowedMisses;

    private int currentMisses = 0;

    public Session(Word answer) {
        this(answer, DEFAULT_MAX_ALLOWED_MISSES);
    }

    public Session(Word answer, int maxAllowedMisses) {
        this.answer = answer;
        this.maxAllowedMisses = maxAllowedMisses;
        hint = new char[answer.getLength()];
        Arrays.fill(hint, '*');
    }

    public GuessState guess(char guess) {
        if (answer.contain(guess)) {
            return hitCase(guess);
        } else {
            return missCase();
        }
    }

    public GuessState giveUp() {
        return new Defeat(getCurrentHint(), currentMisses, maxAllowedMisses);
    }

    public int getMaxAllowedMisses() {
        return maxAllowedMisses;
    }

    public String getCurrentHint() {
        return String.valueOf(hint);
    }

    private GuessState hitCase(char guess) {
        updateHint(guess);
        if (hasUnresolvedLetters()) {
            return new Hit(getCurrentHint(), currentMisses, maxAllowedMisses);
        }
        return new Victory(getCurrentHint(), currentMisses, maxAllowedMisses);
    }

    private GuessState missCase() {
        currentMisses++;
        if (currentMisses == maxAllowedMisses) {
            return new Defeat(getCurrentHint(), currentMisses, maxAllowedMisses);
        }
        return new Miss(getCurrentHint(), currentMisses, maxAllowedMisses);
    }

    private void updateHint(char guess) {
        List<Integer> hitIndexes = answer.getLetterIndexes(guess);
        for (int index : hitIndexes) {
            hint[index] = guess;
        }
    }

    private boolean hasUnresolvedLetters() {
        for (char letter : hint) {
            if (letter == '*') {
                return true;
            }
        }
        return false;
    }
}
