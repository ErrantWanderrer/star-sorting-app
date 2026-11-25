package com.team.starsort.filling;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.team.starsort.collection.CustomArrayList;
import com.team.starsort.collection.CustomList;
import com.team.starsort.model.Star;

public class FileFiller implements DataFiller<Star> {

	private final Path filePath;

	public FileFiller(Path filePath) {

		this.filePath = filePath;

	}
	@Override
	public CustomList<Star> fill(int size) throws Exception {
		if (size < 0) {
		    return new CustomArrayList<>(); 
		}
		CustomList<Star> list = new CustomArrayList<>();
		try (Stream<String> lines = Files.lines(filePath)) {
            lines.limit(size)                      
                 .map(this::parseLine)             
                 .filter(star -> star != null)     
                 .forEach(list::add);              
        }
		
		return list;
	}
	private Star parseLine(String line) {
        try {
            String[] parts = line.split(";");
            
            if (parts.length != 3) {
                System.out.println("Ошибка формата (ожидается 3 поля): " + line);
                return null; 
            }

            String name = parts[0].trim();
            double magnitude = Double.parseDouble(parts[1].trim());
            long distance = Long.parseLong(parts[2].trim());

            return new Star.Builder()
                    .name(name)
                    .apparentMagnitude(magnitude)
                    .distanceLightYears(distance)
                    .build();

        } catch (NumberFormatException e) {
            System.out.println("Ошибка чисел (не удалось распарсить): " + line);
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка валидации: " + e.getMessage() + " | Строка: " + line);
            return null;
        }
    }
}
