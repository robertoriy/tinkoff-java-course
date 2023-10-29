package edu.hw3.task7;

import java.util.Comparator;

public class NullEnabledComparator<T extends Comparable<T>> implements Comparator<T> {
    @Override
    public int compare(T a, T b) {
        if (a == null && b == null) {
            return 0;
        } else if (a == null) {
            return -1;
        } else if (b == null) {
            return 1;
        } else {
            return a.compareTo(b);
        }
    }
}
