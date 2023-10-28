package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Frequency {
    private Frequency() {
    }

    public static <T> Map<T, Integer> compute(List<T> elements) {
        if (elements == null) {
            throw new IllegalArgumentException();
        }
        Map<T, Integer> elementFrequency = new HashMap<>();

        for (T elem : elements) {
            elementFrequency.merge(elem, 1, Integer::sum);
        }
        return elementFrequency;
    }
}
