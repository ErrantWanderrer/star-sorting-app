package com.team.starsort.sorting;

import com.team.starsort.collection.CustomArrayList;
import com.team.starsort.collection.CustomList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BubbleSortTest {

    public static void main(String[] args) {
        testSortNormal();
        testSortDuplicates();
        testSortSingleElement();
        testSortEmpty();
        testSortNullList();
        testSortNullComparator();
        System.out.println("Все тесты пройдены!");
    }

    // Методы проверок
    static void testSortNormal() {
        CustomList<Integer> list = createList(5, 2, 9, 1, 6);
        new BubbleSort<Integer>().sort(list, Comparator.naturalOrder());
        assertListEquals(List.of(1, 2, 5, 6, 9), list, "testSortNormal");
    }

    static void testSortDuplicates(){
        CustomList<Integer> list = createList(2, 5, 2, 1, 4, 3, 4);
        new BubbleSort<Integer>().sort(list, Comparator.naturalOrder());
        assertListEquals(List.of(1, 2, 2, 3, 4, 4, 5), list, "testSortDuplicates");
    }

    static void testSortSingleElement(){
        CustomList<Integer> list = createList(4);
        new BubbleSort<Integer>().sort(list, Comparator.naturalOrder());

        if (list.size() > 1){
            throw new AssertionError("testSortSingleElement упал: размер списка больше 1!");
        }

        System.out.println("testSortSingleElement - OK!");
    }

    static void testSortEmpty() {
        CustomList<Integer> list = new CustomArrayList<>();
        new BubbleSort<Integer>().sort(list, Comparator.naturalOrder());
        assertListEquals(List.of(), list, "testSortEmpty");
    }

    static void testSortNullList() {
        try {
            new BubbleSort<Integer>().sort(null, Comparator.naturalOrder());
            throw new AssertionError("testSortNullList упал: ожидалось что список будет null!");
        } catch (NullPointerException e) {
            System.out.println("testSortNullList - OK!");
        }
    }

    static void testSortNullComparator(){
        CustomList<Integer> list = new CustomArrayList<>();
        try {
            new BubbleSort<Integer>().sort(list, null);
            throw new AssertionError("testSortNullComparator упал: ожидалось что компаратор будет null!");
        } catch (NullPointerException e) {
            System.out.println("testSortNullComparator - OK!");
        }
    }

    // вспомогательные методы
    private static CustomList<Integer> createList(int... values) {
        CustomList<Integer> result = new CustomArrayList<>();
        for (int v : values) {
            result.add(v);
        }
        return result;
    }

    private static <T> void assertListEquals(List<Integer> expected, CustomList<T> actual, String testName) {
        List<T> temp = new ArrayList<>();
        for (T item : actual) {
            temp.add(item);
        }

        if (!expected.equals(temp)){
            throw new AssertionError(testName + " упал: ожидалось " + expected + ", получилось " + actual + "!");
        }

        System.out.println(testName + " - OK!");
    }
}
