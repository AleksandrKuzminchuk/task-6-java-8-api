package main.java.patterns;

import java.time.format.DateTimeFormatter;

public class FormatConstants {
    public static final DateTimeFormatter DATE_FORMAT_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
    public static final DateTimeFormatter BOARD_TIME_FORMAT_PATTERN = DateTimeFormatter.ofPattern("mm:ss.SSS");

    private FormatConstants() {
    }
}
