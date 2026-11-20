package com.team.starsort.filling;

import java.nio.file.Path;

import com.team.starsort.collection.CustomArrayList;
import com.team.starsort.collection.CustomList;
import com.team.starsort.model.Star;

public class FileFiller implements DataFiller<Star> {

	private final Path filePath;

	public FileFiller(Path filePath) {

		this.filePath = filePath;

	}

	public CustomList<Star> fill(int size) throws Exception {
		CustomList<Star> list = new CustomArrayList<>();
		return list;

	}
}
