package pl.samouczekprogramisty.szs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LotteryMachine {
    private final static Pattern FIELD_PATTERN = Pattern.compile("^\"(.+)\"$");

    private final String inputPath;
    private final int noWinners;

    private final List<String> participants = new LinkedList<>();
    private final List<String> winners = new LinkedList<>();

    public LotteryMachine(String inputPath, int noWinners) throws IOException {
        this.inputPath = inputPath;
        this.noWinners = noWinners;
        parseInputFile();
    }

    private void parseInputFile() throws IOException {
        BufferedReader reader = null;
        boolean firstLine = true;
        try {
            reader = new BufferedReader(new FileReader(inputPath));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                if (firstLine) { // we're skipping header
                    firstLine = false;
                    continue;
                }
                String[] fields = line.split(",");
                participants.add(parseField(fields[2]));
                String email = parseField(fields[4]);
                if (email != null) {
                    participants.add(email);
                }
            }
        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private static String parseField(String field) {
        Matcher fieldMatcher = FIELD_PATTERN.matcher(field);
        if (fieldMatcher.matches()) {
            return fieldMatcher.group(1);
        }
        return null;
    }

    private List<String> getWinners() {
        return winners;
    }

    private void draw() {
        Collections.shuffle(participants);
        winners.clear();
        int i = 0;
        for(String participat : participants) {
            if (i == noWinners) {
                break;
            }
            winners.add(participat);
            i++;
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: <sciezka wejsciowa> <liczba wygranych>");
            System.exit(1);
        }
        String inputPath = args[0];  // it's going to be validated by BufferedReader ;)
        int noWinners = validateNoWinners(args[1]);
        LotteryMachine lotteryMachine = new LotteryMachine(inputPath, noWinners);
        lotteryMachine.draw();
        System.out.println("Zwyciezcy:");
        for (String winner : lotteryMachine.getWinners()) {
            System.out.println(winner);
        }
    }

    private static int validateNoWinners(String noWinnersArg) {
        int noWinners = Integer.parseInt(noWinnersArg);
        if (noWinners < 1) {
            throw new RuntimeException("Liczba wygranych " + noWinners + " jest niepoprawna!");
        }
        return noWinners;
    }
}
