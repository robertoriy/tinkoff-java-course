package edu.hw3.task7;

import java.util.Comparator;

public class NullEnabledComparator<T> implements Comparator<T> {
    private final Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public NullEnabledComparator(Comparator<? super T> comparator) {
        this.comparator = (Comparator<T>) comparator;
    }

    @Override
    public int compare(T a, T b) {
        if (a == null && b == null) {
            return 0;
        } else if (a == null) {
            return -1;
        } else if (b == null) {
            return 1;
        } else {
            return comparator.compare(a, b);
        }
    }
}
