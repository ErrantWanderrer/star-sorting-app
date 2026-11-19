
package com.team.starsort.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CustomArrayList<T> implements CustomList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Размер не может быть отрицательным");
        }

        elements = new Object[initialCapacity];
        size = 0;
    }

    @Override
    public void add(T element) {
        Objects.requireNonNull(element, "Нельзя добавить пустой элемент");

        if (size == elements.length) {
            grow();
        }

        elements[size] = element;
        size++;
    }

    private void grow() {
        int newCapacity;

        if (elements.length == 0) {
            newCapacity = DEFAULT_CAPACITY;
        } else {
            newCapacity = elements.length * 2;
        }

        Object[] newElements = new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
    }
}
