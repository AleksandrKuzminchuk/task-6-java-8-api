package main.java.patterns;

public class ValidationPatternConstants {

    public static final String PARTICIPANT_INFO = "^[A-Z]{3}_\\D+_\\D+$";
    public static final String DATE_TIME = "^\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d{2}:\\d{2}\\.\\d{3}$";
    private ValidationPatternConstants() {
    }
}
