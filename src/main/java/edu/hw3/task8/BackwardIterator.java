package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BackwardIterator<E> implements Iterator<E> {
    private final ListIterator<E> cursor;

    public BackwardIterator(List<E> list) {
        if (list == null) {
            throw new IllegalArgumentException("List must be not null");
        }
        cursor = list.listIterator(list.size());
    }

    @Override
    public boolean hasNext() {
        return cursor.hasPrevious();
    }

    @Override
    public E next() {
        if (hasNext()) {
            return cursor.previous();
        }
        throw new NoSuchElementException();
    }
}
