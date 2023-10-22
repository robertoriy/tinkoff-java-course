package edu.project1.guess;

public sealed interface GuessState permits NotStarted, Defeat, Victory, Hit, Miss {
    String hint();

    int currentMisses();

    int maxAllowedMisses();

    String message();
}
