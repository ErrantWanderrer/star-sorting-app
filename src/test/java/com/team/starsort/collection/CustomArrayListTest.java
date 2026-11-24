
package com.team.starsort.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomArrayListTest {

    public static void main(String[] args) {
        testDefaultConstructor();
        testConstructorWithCapacity();
        testConstructorWithZeroCapacity();
        testConstructorWithNegativeCapacity();
        testAddAndGet();
        testAddNull();
        testResizeWhenCapacityExceeded();
        testResizeFromZeroCapacity();
        testGetNegativeIndex();
        testGetIndexEqualToSize();
        testSetElement();
        testSetNull();
        testSetInvalidIndex();
        testIterator();
        testIteratorOnEmptyList();
        testIteratorNextAfterEnd();
        testForEach();

        System.out.println("All CustomArrayListTest passed");
    }

    //конструкторы

    static void testDefaultConstructor() {
        CustomArrayList<String> list = new CustomArrayList<>();
        assertEq(0, list.size(), "default constructor");
    }

    static void testConstructorWithCapacity() {
        CustomArrayList<String> list = new CustomArrayList<>(5);
        assertEq(0, list.size(), "constructor with capacity");
    }

    static void testConstructorWithZeroCapacity() {
        CustomArrayList<String> list = new CustomArrayList<>(0);
        list.add("A");
        assertEq("A", list.get(0), "zero capacity");
    }

    static void testConstructorWithNegativeCapacity() {
        assertThrows(() -> new CustomArrayList<String>(-1),
        IllegalArgumentException.class, "negative capacity");
    }

    //добавление элементов

    static void testAddAndGet() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");

        assertEq(2, list.size(), "add size");
        assertEq("A", list.get(0), "first element");
        assertEq("B", list.get(1), "second element");
    }

    static void testAddNull() {
        CustomArrayList<String> list = new CustomArrayList<>();

        assertThrows(() -> list.add(null),
        NullPointerException.class, "add null");
    }

    static void testResizeWhenCapacityExceeded() {
        CustomArrayList<Integer> list = new CustomArrayList<>();

        for (int i = 0; i < 11; i++) {
            list.add(i);
        }

        assertEq(11, list.size(), "resize size");
        assertEq(10, list.get(10), "resize last element");
    }

    static void testResizeFromZeroCapacity() {
        CustomArrayList<Integer> list = new CustomArrayList<>(0);

        list.add(1);

        assertEq(1, list.size(), "resize from zero");
        assertEq(1, list.get(0), "resize from zero element");
    }

    //получение элементов
    static void testGetNegativeIndex() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");

        assertThrows(() -> list.get(-1),
        IndexOutOfBoundsException.class, "negative index");
    }

    static void testGetIndexEqualToSize() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");

        assertThrows(() -> list.get(1),
        IndexOutOfBoundsException.class, "index equal to size");
    }

    //замена элементов
    static void testSetElement() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");
        list.set(0, "B");

        assertEq("B", list.get(0), "set element");
    }

    static void testSetNull() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");

        assertThrows(() -> list.set(0, null),
        NullPointerException.class, "set null");
    }

    static void testSetInvalidIndex() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");

        assertThrows(() -> list.set(1, "B"),
        IndexOutOfBoundsException.class, "set invalid index");
    }

    //итератор

    static void testIterator() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");

        Iterator<String> iterator = list.iterator();

        assertEq(true, iterator.hasNext(), "iterator has next");
        assertEq("A", iterator.next(), "iterator first");
        assertEq("B", iterator.next(), "iterator second");
        assertEq(false, iterator.hasNext(), "iterator end");
    }

    static void testIteratorOnEmptyList() {
        CustomArrayList<String> list = new CustomArrayList<>();
        Iterator<String> iterator = list.iterator();

        assertEq(false, iterator.hasNext(), "empty iterator");
    }

    static void testIteratorNextAfterEnd() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");

        Iterator<String> iterator = list.iterator();
        iterator.next();

        assertThrows(iterator::next,
        NoSuchElementException.class, "iterator next after end");
    }


    static void testForEach() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        String result = "";

        for (String element : list) {
            result += element;
        }

        assertEq("ABC", result, "for-each");
    }

    //хелперы

    static void assertEq(Object expected, Object actual, String ctx) {
        if (expected == null && actual == null) return;
        if (expected == null || !expected.equals(actual)) {
            throw new AssertionError(
            "[" + ctx + "] expected=" + expected + " actual=" + actual
            );
        }
    }

    static void assertThrows(Runnable action,
            Class<? extends Throwable> expected,
            String ctx) {
        try {
            action.run();
        } catch (Throwable actual) {
            if (expected.isInstance(actual)) return;
            throw new AssertionError(
            "[" + ctx + "] expected " + expected.getSimpleName()
            + ", actual " + actual.getClass().getSimpleName()
            );
        }

        throw new AssertionError(
        "[" + ctx + "] expected " + expected.getSimpleName()
        + ", none thrown"
        );
    }
}
