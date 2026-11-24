package com.team.starsort.filling;

import com.team.starsort.collection.CustomList;
import com.team.starsort.model.Star;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileFillerTest {
    public static void main(String[] args) throws Exception {
        System.out.println("FileFillerTest");
        
        // тестируем  падение программы при чтении битого файла
        // 1 строка валидная, 2 строки битые (нет разделителей и буквы вместо цифр)
        Path testFile = Path.of("test_file_filler.txt");
        String content = "Sirius;-1.46;8\n" +
                         "Corrupted line without separators\n" +
                         "Vega;abc;100\n";
        Files.writeString(testFile, content);

        FileFiller filler = new FileFiller(testFile);

        // запрашиваем 10 элементов , но валидный только 1
        CustomList<Star> stars = filler.fill(10);

        // 
        if (stars.size() == 1) {
            System.out.println("TEST PASS: Corrupted lines were skipped, 1 valid star loaded");
        } else {
            System.out.println("TEST FAIL: Expected 1 star, but got " + stars.size());
        }

        // Следующий тест - передаём отрицательный размер
        // проверка на исключение при lines.limit(size < 0).
        CustomList<Star> emptyStars = filler.fill(-5);
        
        if (emptyStars.size() == 0) {
            System.out.println("TEST PASS: Negative size handled correctly (returned empty list)");
        } else {
            System.out.println("TEST FAIL: Negative size handling failed");
        }

        Files.deleteIfExists(testFile); // очистка временного файла
    }
}