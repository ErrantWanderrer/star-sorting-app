package com.team.starsort.sorting;

import com.team.starsort.collection.CustomList;

import java.util.Comparator;

/**
 * Паттерн Стратегия: интерфейс алгоритма сортировки.
 * Реализации - BubbleSortStrategy, QuickSortStrategy, MergeSortStrategy.
 * @param <T> тип элементов коллекции
 */
public interface SortStrategy<T> {

    /**
     * Отсортировать коллекцию по заданному компаратору.
     * @param list коллекция для сортировки
     * @param comparator компаратор по одному полю
     */
    void sort(CustomList<T> list, Comparator<T> comparator);
}