package com.team.starsort.collection;

import java.util.Iterator;

/**
 * Кастомная коллекция.
 * Минимальный контракт: add / get / size + iterator() для for-each и .stream().
 */
public interface CustomList<T> extends Iterable<T> {

    /**
     * Добавить элемент в конец коллекции.
     * Реализация отвечает за расширение внутреннего массива при переполнении.
     */
    void add(T element);

    /**
     * Получить элемент по индексу.
     *
     * @throws IndexOutOfBoundsException если индекс вне [0, size())
     */
    T get(int index);

    /**
     * Заменить элемент по индексу.
     * Нужен сортировкам для перестановки элементов на месте (in-place).
     *
     * @throws IndexOutOfBoundsException если индекс вне [0, size())
     */
    void set(int index, T element);

    /**
     * Количество элементов в коллекции.
     */
    int size();

 default boolean isEmpty() {
    return size() == 0;
 }

    /**
     * Итератор для for-each и Stream API. Наследуется от Iterable.
     * Без него .stream() не заработает.
     */
    @Override
    Iterator<T> iterator();
}