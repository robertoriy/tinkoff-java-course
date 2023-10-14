package edu.hw1.task7;

public final class CyclicBitShift {
    private static final int RADIX = 2;

    private CyclicBitShift() {

    }

    public static int rotateLeft(int number, int shift) {
        validatePositiveInteger(number);

        if (shift < 0) {
            return rotateRight(number, -shift);
        }
        String binaryResult = getBinaryResult(number, shift, Direction.LEFT);

        return Integer.parseInt(binaryResult, RADIX);
    }

    public static int rotateRight(int number, int shift) {
        validatePositiveInteger(number);

        if (shift < 0) {
            return rotateLeft(number, -shift);
        }
        String binaryResult = getBinaryResult(number, shift, Direction.RIGHT);

        return Integer.parseInt(binaryResult, RADIX);
    }

    private static void validatePositiveInteger(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive integer");
        }
    }

    private static String getBinaryResult(int number, int shift, Direction direction) {
        String binary = Integer.toBinaryString(number);
        int actualShift = shift % binary.length();

        if (actualShift == 0) {
            return binary;
        }
        return switch (direction) {
            case LEFT -> swapHalves(binary, actualShift);
            case RIGHT -> swapHalves(binary, binary.length() - actualShift);
        };
    }

    private static String swapHalves(String input, int delimiterIndex) {
        if (delimiterIndex >= input.length()) {
            return input;
        }
        return input.substring(delimiterIndex) + input.substring(0, delimiterIndex);
    }

    enum Direction {
        LEFT, RIGHT
    }
}
