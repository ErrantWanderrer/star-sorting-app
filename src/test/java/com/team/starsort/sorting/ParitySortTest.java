package com.team.starsort.sorting;

import com.team.starsort.collection.CustomArrayList;
import com.team.starsort.collection.CustomList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParitySortTest {

    public static void main(String[] args){
        testSortNormal();
        testSortEmpty();
        testSortNullList();
        System.out.println("Все тесты пройдены!");
    }

    // Методы проверок
    static void testSortNormal() {
        ParitySort<Integer> paritySort = createParitySort();
        CustomList<Integer> list = createList(1, 4, 3, 2, 5);
        paritySort.sort(list, Comparator.naturalOrder());
        assertListEquals(List.of(1, 2, 3, 4, 5), list, "testSortNormal");
    }

    static void testSortEmpty(){
        ParitySort<Integer> paritySort = createParitySort();
        CustomList<Integer> list = createList();
        paritySort.sort(list, Comparator.naturalOrder());
        assertListEquals(List.of(), list, "testSortEmpty");
    }

    static void testSortNullList(){
        ParitySort<Integer> paritySort = createParitySort();

        try {
            paritySort.sort(null, Comparator.naturalOrder());
            throw new AssertionError("testSortNullList упал: ожидалось что будет null!");
        } catch (NullPointerException e) {
            System.out.println("testSortNullList — OK!");
        }
    }

    // вспомогательные методы
    private static ParitySort<Integer> createParitySort() {
        return new ParitySort<Integer>(n -> n.longValue(), CustomArrayList::new, new BubbleSort<>());
    }

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
