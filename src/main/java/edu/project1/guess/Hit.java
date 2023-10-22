package edu.project1.guess;

public record Hit(String hint, int currentMisses, int maxAllowedMisses) implements GuessState {
    @Override
    public String message() {
        return "You right! Your hint is: " + hint() + "\nmistake " + currentMisses() + " out of " + maxAllowedMisses();
    }
}
