package edu.project1.controller;

import edu.project1.dictionary.Dictionary;
import edu.project1.guess.GuessState;
import edu.project1.guess.Hit;
import edu.project1.guess.Miss;
import edu.project1.guess.NotStarted;
import edu.project1.word.Word;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConsoleHangman {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String GIVE_UP = "exit";
    private final Dictionary dictionary;

    public ConsoleHangman(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void run() {
        Word answer = new Word(dictionary.getRandomWord());
        Session session = new Session(answer);
        GuessState result = new NotStarted(session.getCurrentHint(), 0, session.getMaxAllowedMisses());
        printState(result);

        Scanner scanner = new Scanner(System.in);

        while (result instanceof Hit || result instanceof Miss || result instanceof NotStarted) {
            LOGGER.info("Enter a letter or type 'exit' to give up:");
            String userInput = scanner.nextLine();
            LOGGER.info("You entered: {}", userInput);

            if (isInputInvalid(userInput)) {
                LOGGER.info("Invalid input. Try again!");
                continue;
            }
            result = tryGuess(session, userInput);
            printState(result);
        }
        scanner.close();
    }

    private GuessState tryGuess(Session session, String input) {
        if (input.equalsIgnoreCase(GIVE_UP)) {
            return session.giveUp();
        }
        return session.guess(input.charAt(0));
    }

    private void printState(GuessState guess) {
        String message = guess.message();
        LOGGER.info(message);
    }

    public boolean isInputInvalid(String input) {
        if (input == null || input.length() == 0) {
            return true;
        }
        return !input.equalsIgnoreCase(GIVE_UP) && (input.length() != 1 || !Character.isLetter(input.charAt(0)));
    }
}
