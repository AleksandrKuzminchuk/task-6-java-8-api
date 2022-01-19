package main.java.utils;

import main.java.exceptions.ExceptionsHandlingConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class ReaderFile {
    public List<String> readLines(String fileName) throws IOException {

        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException(ExceptionsHandlingConstants.EMPTY_FILE_NAME);
        }

        try {
            List<String> lines = Files.lines(Paths.get("files" + "\\" + fileName).toAbsolutePath()).collect(Collectors.toList());
            if (lines.isEmpty()) {
                throw new IllegalArgumentException(ExceptionsHandlingConstants.EMPTY_FILE);
            }

            return lines;

        } catch (NullPointerException e) {
            throw new NoSuchFileException(ExceptionsHandlingConstants.NO_FILE);
        }
    }
}
