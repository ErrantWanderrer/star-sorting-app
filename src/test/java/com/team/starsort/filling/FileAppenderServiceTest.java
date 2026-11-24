package com.team.starsort.filling;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.team.starsort.collection.CustomArrayList;
import com.team.starsort.collection.CustomList;
import com.team.starsort.io.FileAppenderService;
import com.team.starsort.model.Star;

public class FileAppenderServiceTest {
    public static void main(String[] args) throws Exception {
        System.out.println("Executing FileAppenderServiceTest");

        // создаем временный файл и сервис записи
        Path testFile = Path.of("test_io_append.txt");
        FileAppenderService appender = new FileAppenderService(testFile);
        
        CustomList<Star> stars = new CustomArrayList<>();
        stars.add(new Star.Builder().name("Star-1").apparentMagnitude(1.0).distanceLightYears(10).build());
        stars.add(new Star.Builder().name("Star-2").apparentMagnitude(2.0).distanceLightYears(20).build());

        // Вызываем запись два раза 
        // функция записи перезаписывает файл с нуля вместо того, чтобы дописывать 
        appender.appendStars(stars);
        appender.appendStars(stars);
        appender.appendFoundCount("Test", 5);

        // считываем фйал и проверяем количество строк
        // ожидаем 5 строк
        // Если  режим APPEND не работает  файл перезапишется и строк станет 3.
        List<String> lines = Files.readAllLines(testFile);
        
        if (lines.size() == 5) {
            System.out.println("TEST PASS: APPEND mode works correctly. Data was appended, not overwritten.");
        } else {
            System.out.println("TEST FAIL: Expected 5 lines, but got " + lines.size());
        }

        Files.deleteIfExists(testFile); // очистка
    }
}