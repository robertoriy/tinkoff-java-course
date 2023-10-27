package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<E> implements Iterator<E> {
    private final List<E> list;
    private int cursor;

    BackwardIterator(List<E> list) {
        this.list = list;
        cursor = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return cursor >= 0;
    }

    @Override
    public E next() {
        if (hasNext()) {
            E next = list.get(cursor);
            cursor--;
            return next;
        }
        throw new NoSuchElementException();
    }
}
