package com.team.starsort.sorting;
import com.team.starsort.collection.CustomList;
import java.util.Comparator;
import java.util.Objects;

public class SortContext<T> {
    private CustomList<T> listElements;
    private SortStrategy<T> strategy;
    private Comparator<T> comparator;

    public SortContext(CustomList<T> listElements, SortStrategy<T> strategy, Comparator<T> comparator){
        this.listElements = Objects.requireNonNull(listElements, "Список элементов не должен быть null");
        this.strategy = Objects.requireNonNull(strategy, "Нужно выбрать стратегию сортировки");
        this.comparator = Objects.requireNonNull(comparator, "Компоратор не должен быть null");
    }

    public void setStrategy(SortStrategy<T> strategy, Comparator<T> comparator){
        this.strategy = Objects.requireNonNull(strategy, "Нужно выбрать стратегию сортировки");
        this.comparator = Objects.requireNonNull(comparator, "Компоратор не должен быть null");
    }

    public void sort(){
        strategy.sort(listElements, comparator);
    }

// TEMP!!! Delete if not needed!!!

//    public CustomList<T> getSortedListElements(){
//        return strategy.sort(listElements, comparator);
//    }
}
