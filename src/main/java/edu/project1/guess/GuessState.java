package edu.project1.guess;

public sealed interface GuessState permits NotStarted, Defeat, Miss, Hit, Victory {
    String hint();

    int currentMisses();

    int maxAllowedMisses();

    String message();
}
