package edu.hw1.task3;

public final class NestedArray {
    private NestedArray() {
    }

    public static boolean isNestable(int[] a, int[] b) {
        if (!isValidArray(a) || !isValidArray(b)) {
            return false;
        }

        ArrayProperties aContent = findMinAndMax(a);
        ArrayProperties bContent = findMinAndMax(b);

        return (aContent.min > bContent.min) && (aContent.max < bContent.max);
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
