package com.team.starsort.filling;

import com.team.starsort.collection.CustomList;

/**
 * Паттерн Стратегия: интерфейс заполнения коллекции.
 * Реализации - FileFiller, RandomFiller, ManualFiller.
 * @param <T> тип элементов коллекции
 */
public interface DataFiller<T> {

    /**
     * Заполнить и вернуть новую коллекцию.
     * @param size сколько элементов создать
     * @return заполненная коллекция
     * @throws Exception если источник данных недоступен или данные невалидны
     */
    CustomList<T> fill(int size) throws Exception;
}