package main.java.pojo;

import java.time.Duration;
import java.time.LocalDateTime;

public class Lap implements Comparable<Lap> {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private Duration fastestLap;

    private Participant participant;

    public Lap(LocalDateTime startDateTime, LocalDateTime endDateTime, Duration fastestLap, Participant participant) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.participant = participant;
        this.fastestLap = fastestLap;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Duration getFastestLap() {
        return fastestLap;
    }

    @Override
    public int compareTo(Lap lap) {
        if (lap == null) {
            return -1;
        }

        return this.fastestLap.compareTo(lap.fastestLap);
    }
}
