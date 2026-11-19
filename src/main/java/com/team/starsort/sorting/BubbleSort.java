package com.team.starsort.sorting;
import com.team.starsort.collection.CustomList;
import java.util.Comparator;
import java.util.Objects;

public class BubbleSort<T> implements SortStrategy<T> {

    @Override
    public void sort(CustomList<T> list, Comparator<T> comparator) {
        Objects.requireNonNull(list, "Список не должен быть null");
        Objects.requireNonNull(comparator, "Компаратор не должен быть null");

        if (list.size() <= 1) {
            return;
        }

        boolean swapped;

        for (int i = 0; i < list.size() - 1; i++) {
            swapped = false;

            for (int j = 0; j < list.size() - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
}
