package edu.project1;

import edu.project1.controller.ConsoleHangman;
import edu.project1.dictionary.HandwrittenDictionary;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        String[] words = {"first", "secret", "hello", "world"};
        ConsoleHangman hangman = new ConsoleHangman(new HandwrittenDictionary(words));
        hangman.run();
    }
}
