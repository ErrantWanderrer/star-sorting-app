package com.team.starsort.io;

import java.nio.file.Path;

public class FileAppenderService {

    private final Path filePath;

    public FileAppenderService(Path filePath) {
        this.filePath = filePath;
    }
}
