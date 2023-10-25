package edu.project1.guess;

public record Defeat(String hint, int currentMisses, int maxAllowedMisses) implements GuessState {
    @Override
    public String message() {
        return String.format(
            "You lost! Your hint is: %s. Mistake %d out of %d",
            hint(), currentMisses(), maxAllowedMisses()
        );
    }
}
