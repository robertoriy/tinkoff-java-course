package edu.project1.guess;

public record Defeat(String hint, int currentMisses, int maxAllowedMisses) implements GuessState {
    @Override
    public String message() {
        return "You lost! Your hint is: " + hint() + "\nmistake " + currentMisses() + " out of " + maxAllowedMisses();
    }
}
