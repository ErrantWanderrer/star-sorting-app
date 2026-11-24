package com.team.starsort.sorting;

import com.team.starsort.collection.CustomArrayList;
import com.team.starsort.collection.CustomList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BubbleSortTest {

    public static void main(String[] args) {
        testSortNormal();
        testSortEmpty();
        testSortNullList();
        System.out.println("Все тесты пройдены!");
    }

    // Методы проверок
    static void testSortNormal() {
        CustomList<Integer> list = createList(5, 2, 9, 1, 6);
        new BubbleSort<Integer>().sort(list, Comparator.naturalOrder());

        List<Integer> expected = List.of(1, 2, 5, 6, 9);
        List<Integer> actual = toJavaList(list);

        if (!expected.equals(actual)) {
            throw new AssertionError("testSortNormal упал: ожидалось " + expected + ", получилось " + actual + "!");
        }

        System.out.println("testSortNormal - OK!");
    }

    static void testSortEmpty() {
        CustomList<Integer> list = new CustomArrayList<>();
        new BubbleSort<Integer>().sort(list, Comparator.naturalOrder());

        if (!list.isEmpty()) {
            throw new AssertionError("testSortEmpty упал: список не является пустым!");
        }

        System.out.println("testSortEmpty - OK!");
    }

    static void testSortNullList() {
        try {
            new BubbleSort<Integer>().sort(null, Comparator.naturalOrder());
            throw new AssertionError("testSortNullList упал: ожидалось что будет null!");
        } catch (NullPointerException e) {
            System.out.println("testSortNullList - OK!");
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

    private static List<Integer> toJavaList(CustomList<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            result.add(list.get(i));
        }
        return result;
    }
}
