package com.team.starsort.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import com.team.starsort.collection.CustomList;
import com.team.starsort.model.Star;

public class FileAppenderService {

    private final Path filePath;

    public FileAppenderService(Path filePath) {
        this.filePath = filePath;
    }

    public void appendStars(CustomList<Star> stars) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Star star : stars) {
            lines.add(star.toString());
        }
        Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}