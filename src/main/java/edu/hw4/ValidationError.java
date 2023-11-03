package edu.hw4;

import java.util.stream.Stream;

public record ValidationError(AnimalError error) {
    enum AnimalError {
        EMPTY_NAME("there is no name"),
        NEGATIVE_ATTRIBUTES("attributes must be positive");

        private final String description;

        AnimalError(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
        }

    public static Stream<ValidationError> findErrors(Animal animal) {
        Stream.Builder<ValidationError> errorStream = Stream.builder();
        if (animal.name().isBlank()) {
            errorStream.add(new ValidationError(AnimalError.EMPTY_NAME));
        }
        if (animal.age() < 0 || animal.height() < 0 || animal.weight() < 0) {
            errorStream.add(new ValidationError(AnimalError.NEGATIVE_ATTRIBUTES));
        }
        return errorStream.build();
    }
}
