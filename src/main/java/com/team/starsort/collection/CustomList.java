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
     * Количество элементов в коллекции.
     */
    int size();

    /**
     * Итератор для for-each и Stream API. Наследуется от Iterable.
     * Без него .stream() не заработает.
     */
    @Override
    Iterator<T> iterator();
}