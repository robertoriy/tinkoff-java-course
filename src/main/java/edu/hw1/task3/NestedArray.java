package edu.hw1.task3;

public final class NestedArray {
    private NestedArray() {
    }

    public static boolean isNestable(int[] a, int[] b) {
        if (!isValidArray(a) || !isValidArray(b)) {
            throw new IllegalArgumentException("Arrays must be not empty");
        }
        ArrayProperties propertiesA = findMinAndMax(a);
        ArrayProperties propertiesB = findMinAndMax(b);

        return (propertiesA.min() > propertiesB.min()) && (propertiesA.max() < propertiesB.max());
    }

    private static boolean isValidArray(int[] array) {
        return (array != null && array.length > 0);
    }

    private static ArrayProperties findMinAndMax(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int value : array) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        return new ArrayProperties(min, max);
    }

    record ArrayProperties(int min, int max) {
    }
}
