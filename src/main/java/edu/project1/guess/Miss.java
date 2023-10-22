package edu.project1.guess;

public record Miss(String hint, int currentMisses, int maxAllowedMisses) implements GuessState {
    @Override
    public String message() {
        return "You wrong! Your hint: " + hint() + "\nmistake " + currentMisses() + " out of " + maxAllowedMisses();
    }
}
