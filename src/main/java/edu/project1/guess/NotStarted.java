package edu.project1.guess;

public record NotStarted(String hint, int currentMisses, int maxAllowedMisses) implements GuessState {
    @Override
    public String message() {
        return String.format(
            "Better guess! Your hint is: %s. Mistake %d out of %d",
            hint(), currentMisses(), maxAllowedMisses()
        );
    }
}
