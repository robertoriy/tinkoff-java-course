package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    public static String[] getFieldNames() {
        return new String[] {"name", "type", "sex", "age", "height", "weight", "bites"};
    }

    public int paws() {
        return type.getNumberOfPaws();
    }

    enum Type {
        CAT(4), DOG(4), BIRD(2), FISH(0), SPIDER(8);

        private final int numberOfPaws;

        Type(int numberOfPaws) {
            this.numberOfPaws = numberOfPaws;
        }

        public int getNumberOfPaws() {
            return numberOfPaws;
        }
    }

    enum Sex {
        M, F
    }
}
