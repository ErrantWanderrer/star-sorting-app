package com.team.starsort.sorting;
import com.team.starsort.collection.CustomList;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.function.ToLongFunction;

public class ParitySort<T> implements SortStrategy<T> {
    private final ToLongFunction<T> keyExtraction;
    private final Supplier<CustomList<T>> bufferFactory;
    private final SortStrategy<T> strategy;

    public ParitySort(ToLongFunction<T> keyExtraction, Supplier<CustomList<T>> bufferFactory, SortStrategy<T> strategy){
        this.keyExtraction = keyExtraction;
        this.bufferFactory = bufferFactory;
        this.strategy = strategy;
    }

    @Override
    public void sort(CustomList<T> list, Comparator<T> comparator) {
        Objects.requireNonNull(list, "Список не должен быть null");
        Objects.requireNonNull(comparator, "Компаратор не должен быть null");

        int length = list.size();

        CustomList<T> tempList = bufferFactory.get();

        for (int i = 0; i < length; i++){
            if (keyExtraction.applyAsLong(list.get(i)) % 2 == 0){
                tempList.add(list.get(i));
            }
        }

        if (tempList.size() <= 1){
            return;
        }

        strategy.sort(tempList, comparator);

        int nextElement = 0;
        for (int i = 0; i < length; i++){
            if (keyExtraction.applyAsLong(list.get(i)) % 2 == 0){
                list.set(i, tempList.get(nextElement++));
            }
        }
    }
}
