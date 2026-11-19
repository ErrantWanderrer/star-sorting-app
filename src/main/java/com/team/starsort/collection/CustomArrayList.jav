
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

}