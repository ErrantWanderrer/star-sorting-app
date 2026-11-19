package com.team.starsort.sorting;
import com.team.starsort.collection.CustomList;
import java.util.Comparator;
import java.util.Objects;

public class MergeSort<T> implements SortStrategy<T> {

    @Override
    public void sort(CustomList<T> list, Comparator<T> comparator) {
        Objects.requireNonNull(list, "Список не должен быть null");
        Objects.requireNonNull(comparator, "Компоратор не должен быть null");

        if (list.size() <= 1) {
            return;
        }

        Object[] temp = new Object[list.size()];
        mergeSort(list, temp, 0, list.size() - 1, comparator);
    }

    private void mergeSort(CustomList<T> list, Object[] temp, int left, int right, Comparator<T> comparator) {
        if (left >= right){
            return;
        }

        int middle = left + (right - left) / 2;

        mergeSort(list, temp, left, middle, comparator);
        mergeSort(list, temp, middle + 1, right, comparator);
        merge(list, temp, left, middle, right, comparator);
    }

    private void merge(CustomList<T> list, Object[] temp, int left, int middle, int right, Comparator<T> comparator) {
        for (int i = left; i <= right; i++){
            temp[i] = list.get(i);
        }

        int i = left;
        int j = middle + 1;

        for (int k = left; k <= right; k++){
            if (i > middle){
                list.set(k, (T)temp[j++]);
            } else if (j > right){
                list.set(k, (T)temp[i++]);
            } else if (comparator.compare((T)temp[i], (T)temp[j]) <= 0){
                list.set(k, (T)temp[i++]);
            } else {
                list.set(k, (T)temp[j++]);
            }
        }
    }
}
