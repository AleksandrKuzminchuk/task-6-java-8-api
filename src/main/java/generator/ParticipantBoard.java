package main.java.generator;

import main.java.patterns.FormatConstants;
import main.java.pojo.Lap;

import main.java.pojo.Participant;
import main.java.utils.Parser;
import main.java.utils.ReaderFile;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ParticipantBoard {

    public String buildBoardAfterFirstLap(String startLogFileName, String endLogFileName,
                                          String abbrFileName) throws FileNotFoundException {

        try {
            ReaderFile reader = new ReaderFile();

            List<String> startList = reader.readLines(startLogFileName);
            List<String> endList = reader.readLines(endLogFileName);
            List<String> abbreviationsList = reader.readLines(abbrFileName);

            StringBuilder builder = new StringBuilder();

            int maxTeamNameLength = parseRacers(abbreviationsList).stream().mapToInt(participant -> participant.getTeamName().length()).max().getAsInt();
            int maxRacerNameLength = parseRacers(abbreviationsList).stream().mapToInt(racer -> racer.getName().length()).max().getAsInt();

            generateLapsInfo(startList, endList, abbreviationsList).sorted().forEachOrdered(new Consumer<Lap>() {
                int index = 1;

                @Override
                public void accept(Lap lap) {
                    builder.append(generateBoard(lap, maxRacerNameLength, maxTeamNameLength, index));
                    index++;
                }
            });

            return builder.toString();
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    private Stream<Lap> generateLapsInfo(List<String> startList,
                                         List<String> endList, List<String> abbreviationsList) {

        return parseRacers(abbreviationsList).stream().map(racer -> {
            LocalDateTime startTime = parseLapInfo(startList, racer);
            LocalDateTime endTime = parseLapInfo(endList, racer);

            Duration lapDuration = Duration.between(startTime, endTime);

            return new Lap(startTime, endTime, lapDuration, racer);
        });
    }

    private List<Participant> parseRacers(List<String> abbreviations) {
        Parser parser = new Parser();

        return abbreviations.stream().map(parser::createParticipantFromString).collect(Collectors.toList());
    }

    private LocalDateTime parseLapInfo(List<String> list, Participant participant) {
        Parser parser = new Parser();

        return parser.parseTimeDateFromString(list.stream().filter(line -> line.startsWith(participant.getAbbreviation())).findAny().orElseThrow(() -> new NoSuchElementException("No element in start.log")).substring(3));
    }

    private String generateBoard(Lap lap, int maxRacerNameLength, int maxTeamNameLength, int index) {

        StringBuilder builder = new StringBuilder();

        LocalTime time = LocalTime.ofNanoOfDay(lap.getFastestLap().toNanos());
        String timeOutput = time.format(FormatConstants.BOARD_TIME_FORMAT_PATTERN);

        int spacesName = maxRacerNameLength - lap.getParticipant().getName().length();
        int spacesTeam = maxTeamNameLength - lap.getParticipant().getTeamName().length();

        if (index > 9) {
            spacesName--;
        }

        String line = String.format("%d. %s%s | %s%s | %s", index, lap.getParticipant().getName(),
                repeatChar(spacesName, ' '), lap.getParticipant().getTeamName(), repeatChar(spacesTeam, ' '), timeOutput);

        builder.append(line).append("\n");
        if (index == 15) {
            builder.append(repeatChar(line.length(), '-')).append("\n");
        }
        return builder.toString();
    }

    private String repeatChar(int length, char ch) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            builder.append(ch);
        }

        return builder.toString();
    }

}
