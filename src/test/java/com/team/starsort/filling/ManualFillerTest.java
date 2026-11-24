package com.team.starsort.filling;

import com.team.starsort.collection.CustomList;
import com.team.starsort.model.Star;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class ManualFillerTest {
    public static void main(String[] args) {
        System.out.println("Executing ManualFillerTest");

        // Эмулируем ввод пользователя через ByteArrayInputStream.
        // тестируем на то - зависает ли программа  при неверном вводе.
        // имитируем следующую последовательность: 
        // Пустая строка , валидное имя, текст вместо числа затем коректное название, и 2 валидных значения для величины
        String simulatedInput = "\n" + "Star1\n" + "abc\n" + "Vega\n" + "0.5\n" + "25\n";
        InputStream fakeInput = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner mockScanner = new Scanner(fakeInput);
        
        ManualFiller filler = new ManualFiller(mockScanner);

        // просим ввести 1 звезду
        CustomList<Star> stars = filler.fill(1);

        // программа должна продолжить после ошибки ввода и в итоге создать объект.
        if (stars.size() == 1) {
            System.out.println("TEST PASS: Program survived invalid inputs (empty and text) and created 1 star");
        } else {
            System.out.println("TEST FAIL: Expected 1 star, but got " + stars.size());
        }
        
        // убеждаемся, что объект создан именно из валидных данных, которые мы ввели в конце.
        if (stars.size() > 0 && "Vega".equals(stars.get(0).getName())) {
            System.out.println("TEST PASS: Star name was parsed correctly");
        } else {
            System.out.println("TEST FAIL: Star name does not match expected value");
        }
        
        mockScanner.close();
    }
}