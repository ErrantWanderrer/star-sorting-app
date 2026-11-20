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
		CustomList<Star> list = new CustomArrayList<>();
		try (Stream<String> lines = Files.lines(filePath)) {
            lines.limit(size)                      // Берем только нужное кол-во строк
                 .map(this::parseLine)             // Превращаем строку в объект Star
                 .filter(star -> star != null)     // Отбрасываем битые строки (null)
                 .forEach(list::add);              // Добавляем в наш список
        }
		
		return list;

	}
}
