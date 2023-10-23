package edu.project1.guess;

public record Hit(String hint, int currentMisses, int maxAllowedMisses) implements GuessState {
    @Override
    public String message() {
        return String.format(
            "You right! Your hint is: %s. Mistake %d out of %d",
            hint(), currentMisses(), maxAllowedMisses()
        );
    }
}
