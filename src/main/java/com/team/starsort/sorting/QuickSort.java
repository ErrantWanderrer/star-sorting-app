package com.team.starsort.sorting;

import com.team.starsort.collection.CustomList;

import java.util.Comparator;
import java.util.Objects;

public class QuickSort<T> implements SortStrategy<T> {

    @Override
    public void sort(CustomList<T> list, Comparator<T> comparator) {
        Objects.requireNonNull(list, "Список не должен быть null");
        Objects.requireNonNull(comparator, "Компаратор не должен быть null");

        if (list.size() <= 1) {
            return;
        }

        quickSort(list, 0, list.size() - 1, comparator);
    }

    private void quickSort(CustomList<T> list, int low, int high, Comparator<T> comparator) {
        if (low >= high) {
            return;
        }

        int pivotIndex = partition(list, low, high, comparator);

        // Сортируем левую часть
        quickSort(list, low, pivotIndex - 1, comparator);
        // Сортируем правую часть
        quickSort(list, pivotIndex + 1, high, comparator);
    }

    private int partition(CustomList<T> list, int low, int high, Comparator<T> comparator) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(CustomList<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
