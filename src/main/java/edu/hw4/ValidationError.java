package edu.hw4;

import java.util.stream.Stream;

public record ValidationError(String field, AnimalError error) {
    @SuppressWarnings("checkstyle:MagicNumber")
    public static Stream<ValidationError> errors(Animal animal) {
        String[] fields = Animal.getFieldNames();
        Stream.Builder<ValidationError> errorStream = Stream.builder();

        addStringError(animal.name(), fields[0], errorStream);

        addNullError(animal.type(), fields[1], errorStream);
        addNullError(animal.sex(), fields[2], errorStream);

        addNegativeValueError(animal.age(), fields[3], errorStream);
        addNegativeValueError(animal.height(), fields[4], errorStream);
        addNegativeValueError(animal.weight(), fields[5], errorStream);

        return errorStream.build();
    }

    private static void addStringError(String fieldValue, String fieldName, Stream.Builder<ValidationError> errors) {
        if (fieldValue == null) {
            errors.add(new ValidationError(fieldName, AnimalError.NULL_VALUE));
        } else if (fieldValue.isBlank()) {
            errors.add(new ValidationError(fieldName, AnimalError.EMPTY_STRING));
        }
    }

    private static void addNullError(Object fieldValue, String fieldName, Stream.Builder<ValidationError> errors) {
        if (fieldValue == null) {
            errors.add(new ValidationError(fieldName, AnimalError.NULL_VALUE));
        }
    }

    private static void addNegativeValueError(int value, String fieldName, Stream.Builder<ValidationError> errors) {
        if (value < 0) {
            errors.add(new ValidationError(fieldName, AnimalError.NEGATIVE_VALUE));
        }
    }

    enum AnimalError {
        EMPTY_STRING("Must be not empty"),
        NEGATIVE_VALUE("Must be positive"),
        NULL_VALUE("Must be not null");

        private final String description;

        AnimalError(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
