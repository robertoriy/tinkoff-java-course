package edu.project1.guess;

public record Victory(String hint, int currentMisses, int maxAllowedMisses) implements GuessState {
    @Override
    public String message() {
        return "You won! Answer is: " + hint() + "\nmistake " + currentMisses() + " out of " + maxAllowedMisses();
    }
}
