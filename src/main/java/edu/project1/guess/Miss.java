package edu.project1.guess;

public record Miss(String hint, int currentMisses, int maxAllowedMisses) implements GuessState {
    @Override
    public String message() {
        return String.format(
            "You wrong! Your hint: %s. Mistake %d out of %d",
            hint(),
            currentMisses(),
            maxAllowedMisses()
        );
    }
}
