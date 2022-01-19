package main.java;

import main.java.generator.ParticipantBoard;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        String start = "start.log";
        String end = "end.log";
        String abbr = "abbreviations.txt";

        ParticipantBoard board = new ParticipantBoard();

        System.out.println(board.buildBoardAfterFirstLap(start, end, abbr));


    }
}
