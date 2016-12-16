package pl.samouczekprogramisty.szs.aoc2016.day15;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sculpture {
    private final List<Disc> discs;

    private final static Pattern DISC_DESCRIPTION_PATTERN = Pattern.compile("Disc #(\\d+) has (\\d+) positions; at time=0, it is at position (\\d+).");

    public Sculpture(Disc ... discs) {
        this.discs = Arrays.asList(discs);
    }

    public static Sculpture createScrulpture(List<String> specification) {
        Disc[] discs = new Disc[specification.size()];

        for (String line : specification) {
            Matcher matcher = DISC_DESCRIPTION_PATTERN.matcher(line);
            if (!matcher.matches()) {
                throw new IllegalArgumentException(line);
            }
            int distLevel = Integer.parseInt(matcher.group(1));
            int discIndex = distLevel - 1;
            int numberOfPositions = Integer.parseInt(matcher.group(2));
            int startingPosition = Integer.parseInt(matcher.group(3));
            discs[discIndex] = new Disc(numberOfPositions, startingPosition, distLevel);
        }
        return new Sculpture(discs);
    }

    public int canFallThroughAtTime() {
        int time = 0;
        boolean cannFallTrhough = false;
        while (!cannFallTrhough) {
            cannFallTrhough = true;
            for (Disc disc : discs) {
                cannFallTrhough &= disc.canFallThroughAtTime(time);
                if (!cannFallTrhough) {
                    break;
                }
            }
            time++;
        }

        return time;
    }
}
