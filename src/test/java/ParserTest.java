package test.java;

import main.java.pojo.Participant;
import main.java.utils.Parser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    private Parser parser = new Parser();

    @Test
    void shouldReturnParticipantFromString() {

        Participant actual = parser.createParticipantFromString("SVF_Sebastian Vettel_FERRARI");

        String expectedAbbr = "SVF";
        String expectedFullName = "Sebastian Vettel";
        String expectedTeamName = "FERRARI";

        assertEquals(expectedAbbr, actual.getAbbreviation());
        assertEquals(expectedFullName, actual.getName());
        assertEquals(expectedTeamName, actual.getTeamName());
    }

    @Test
    @DisplayName("createParticipantFromString")
    void shouldReturnIllArgExcWhenArgumentIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    parser.createParticipantFromString(null);
                });
    }

    @Test
    @DisplayName("createParticipantFromString")
    void shouldReturnIllArgExcWhenArgumentIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    parser.createParticipantFromString("");
                });
    }

    @Test
    @DisplayName("createParticipantFromString")
    void shouldReturnIllArgExcWhenArgumentHaveWrongFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    parser.createParticipantFromString("df");
                });
    }

    @Test
    void shouldReturnLocalDateTime() {
        LocalDateTime actual = parser.parseTimeDateFromString("2018-05-24_12:05:58.778");
        LocalDateTime expected = LocalDateTime.of(2018, 05, 24, 12, 05, 58, 778000000);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("parseTimeDateFromString")
    void shouldReturnIllArgExcWhenArgIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    parser.parseTimeDateFromString(null);
                });
    }

    @Test
    @DisplayName("parseTimeDateFromString")
    void shouldReturnIllArgExcWhenArgIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    parser.parseTimeDateFromString("");
                });
    }

    @Test
    @DisplayName("parseTimeDateFromString")
    void shouldReturnIllArgExcWhenTimeFormatWrong() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    parser.parseTimeDateFromString("12:05:58.778_2018-05-24");
                });
    }


}
