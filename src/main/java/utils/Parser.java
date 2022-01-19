package main.java.utils;

import main.java.exceptions.ExceptionsHandlingConstants;
import main.java.patterns.FormatConstants;
import main.java.patterns.ValidationPatternConstants;
import main.java.pojo.Participant;

import java.time.LocalDateTime;

public class Parser {

    public Participant createParticipantFromString(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(ExceptionsHandlingConstants.EMPTY_ARGUMENT);
        }
        if (!string.matches(ValidationPatternConstants.PARTICIPANT_INFO)) {
            throw new IllegalArgumentException(ExceptionsHandlingConstants.ARGUMENT_FORMAT);
        }

        String[] params = string.split("_");

        return new Participant(params[0], params[1], params[2]);
    }

    public LocalDateTime parseTimeDateFromString(String dateTime) {
        if (dateTime == null || dateTime.isEmpty()) {
            throw new IllegalArgumentException(ExceptionsHandlingConstants.EMPTY_ARGUMENT);
        }
        if (!dateTime.matches(ValidationPatternConstants.DATE_TIME)) {
            throw new IllegalArgumentException(ExceptionsHandlingConstants.ARGUMENT_FORMAT);
        }

        return LocalDateTime.parse(dateTime, FormatConstants.DATE_FORMAT_PATTERN);
    }
}
