package edu.project1;

import edu.project1.controller.ConsoleHangman;
import edu.project1.dictionary.HandwrittenDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        String[] words = {"first", "secret", "hello", "world"};
        ConsoleHangman hangman = new ConsoleHangman(new HandwrittenDictionary(words));
        hangman.run();
    }

}
