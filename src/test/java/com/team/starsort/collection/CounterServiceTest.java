
package com.team.starsort.collection;

import java.util.concurrent.ExecutionException;

public class CounterServiceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        testCountOccurrences();
        testElementNotFound();
        testEmptyList();
        testNullList();
        testSingleElement();
        testAllElementsMatch();
        testNoElementsMatch();
        testLessElementsThanThreads();
        testUnevenChunks();
        testNullTarget();

        System.out.println("All CounterServiceTest passed");
    }

    //обычные случаи

    static void testCountOccurrences() throws InterruptedException, ExecutionException {
        CustomList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");
        list.add("A");

        assertEq(3, CounterService.countOccurrences(list, "A"),
        "count occurrences");
    }

    static void testElementNotFound() throws InterruptedException, ExecutionException {
        CustomList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        assertEq(0, CounterService.countOccurrences(list, "D"),
        "element not found");
    }

    //пустые списки

    static void testEmptyList() throws InterruptedException, ExecutionException {
        CustomList<String> list = new CustomArrayList<>();

        assertEq(0, CounterService.countOccurrences(list, "A"),
        "empty list");
    }

    static void testNullList() throws InterruptedException, ExecutionException {
        assertEq(0, CounterService.countOccurrences(null, "A"),
        "null list");
    }

    //границы

    static void testSingleElement() throws InterruptedException, ExecutionException {
        CustomList<String> list = new CustomArrayList<>();
        list.add("A");

        assertEq(1, CounterService.countOccurrences(list, "A"),
        "single element");
    }

    static void testAllElementsMatch() throws InterruptedException, ExecutionException {
        CustomList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");

        assertEq(4, CounterService.countOccurrences(list, "A"),
        "all elements match");
    }

    static void testNoElementsMatch() throws InterruptedException, ExecutionException {
        CustomList<String> list = new CustomArrayList<>();
        list.add("B");
        list.add("C");
        list.add("D");

        assertEq(0, CounterService.countOccurrences(list, "A"),
        "no elements match");
    }

    //работа с потоками

    static void testLessElementsThanThreads() throws InterruptedException, ExecutionException {
        CustomList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");

        assertEq(1, CounterService.countOccurrences(list, "A"),
        "less elements than threads");
    }

    static void testUnevenChunks() throws InterruptedException, ExecutionException {
        CustomList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");
        list.add("A");
        list.add("D");
        list.add("A");

        assertEq(4, CounterService.countOccurrences(list, "A"),
        "uneven chunks");
    }

    //null-элемент для поиска

    static void testNullTarget() throws InterruptedException, ExecutionException {
        CustomList<String> list = new CustomArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        assertEq(0, CounterService.countOccurrences(list, null),
        "null target");
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
}
