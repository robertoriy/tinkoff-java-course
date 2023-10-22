package edu.project1.guess;

public record NotStarted(String hint, int currentMisses, int maxAllowedMisses) implements GuessState {
    @Override
    public String message() {
        return "Better guess! Your hint is: " + hint() + "\nmistake " + currentMisses() + " out of "
            + maxAllowedMisses();
    }
}
