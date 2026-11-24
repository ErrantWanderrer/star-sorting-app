package com.team.starsort.filling;

import com.team.starsort.collection.CustomList;
import com.team.starsort.model.Star;

public class RandomFillerTest {
    public static void main(String[] args) {
        System.out.println("Executing RandomFillerTest");

        // cоздаем объект и задаем ожидаемый размер.
        // Берем 50 элементов чтобы увеличить шанс некоректных значений
        RandomFiller filler = new RandomFiller();
        int expectedSize = 50;

        // вызываем метод генерации
        CustomList<Star> stars = filler.fill(expectedSize);

        // проверка на размер, получится ли столько значений сколько мы запросили
        if (stars.size() == expectedSize) {
            System.out.println("TEST PASS: Generated list size is exactly " + expectedSize);
        } else {
            System.out.println("TEST FAIL: Expected size " + expectedSize + " but got " + stars.size());
        }

        // проверка на валидность
        boolean isDistanceValid = true;
        for (Star s : stars) {
            if (s.getDistanceLightYears() <= 0) {
                isDistanceValid = false;
                break;
            }
        }
        
        if (isDistanceValid) {
            System.out.println("TEST PASS: All generated distances are > 0 ");
        } else {
            System.out.println("TEST FAIL: Found a star with distance <= 0");
        }
    }
}