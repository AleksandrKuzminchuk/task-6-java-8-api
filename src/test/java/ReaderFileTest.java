package test.java;

import main.java.utils.ReaderFile;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderFileTest {

    private ReaderFile reader = new ReaderFile();

    @Test
    void shouldReturnListFromFile() throws IOException {
        List<String> expected = new ArrayList<>();
        expected.add("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER");
        expected.add("SVF_Sebastian Vettel_FERRARI");

        assertEquals(expected, reader.readLines("testFile.txt"));
    }

    @Test
    void shouldReturnLengthOfReaderLines() throws IOException {
        int expected = 19;
        List<String> actual = reader.readLines("abbreviations.txt");
        assertEquals(expected, actual.size());
    }

    @Test
    void shouldReturnNonEmptyLines() throws IOException {
        Random random = new Random();
        List<String> list = reader.readLines("start.log");

        String actual = list.get(random.nextInt(15));
        assertTrue(StringUtils.isNotBlank(actual));
    }

    @Test
    void shouldReturnNonEmptyLine() throws IOException {
        List<String> list = reader.readLines("start.log");

        String actual = list.get(1);
        String expected = "NHR2018-05-24_12:02:49.914";
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnFNFEWhenFileIsNotExist() {
        assertThrows(NoSuchFileException.class,
                () -> {
                    reader.readLines("false");
                });
    }

    @Test
    void shouldReturnIllArgExcWhenFileNameIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    reader.readLines("");
                });
    }

    @Test
    void shouldReturnIllArgExcWhenFileNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    reader.readLines(null);
                });
    }

    @Test
    void shouldReturnEIllArgExcWhenFileEmpty() throws IOException {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    reader.readLines("testFileEmpty");
                });
    }
}
