package edu.project1.guess;

public record Victory(String hint, int currentMisses, int maxAllowedMisses) implements GuessState {
    @Override
    public String message() {
        return String.format(
            "You won! Answer is: %s. Mistake %d out of %d",
            hint(), currentMisses(), maxAllowedMisses()
        );
    }
}
