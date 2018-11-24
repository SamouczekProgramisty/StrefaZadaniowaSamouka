package pl.samouczekprogramisty.szs.aoc2016.day03;

import org.junit.jupiter.api.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PuzzleTest {
    private static final Pattern pattern = Pattern.compile("(\\d+) +(\\d+) +(\\d+)");

    @Test
    public void shouldBeAbleToSolvePuzzle() throws IOException {
        int valid = 0;

        for (String line : InputFileReader.readFileAsLines("day03_input.txt")) {
            int[] sides = parse(line);
            try {
                new Triangle(sides[0], sides[1], sides[2]);
                valid++;
            }
            catch (IllegalArgumentException e) {
                // not important
            }
        }

        assertEquals(1032, valid);
    }


    private int[] parse(String line) {
        Matcher matcher = pattern.matcher(line);
        matcher.find();

        return new int[] {
            Integer.parseInt(matcher.group(1)),
            Integer.parseInt(matcher.group(2)),
            Integer.parseInt(matcher.group(3))
        };
    }

    @Test
    public void shouldBeAbleToSolvePuzzle2() throws IOException {
        int valid = 0;

        List<String> lines = InputFileReader.readFileAsLines("day03_input.txt");
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex += 3) {
            int[] sides1 = parse(lines.get(lineIndex));
            int[] sides2 = parse(lines.get(lineIndex + 1));
            int[] sides3 = parse(lines.get(lineIndex + 2));
            for (int sideIndex = 0; sideIndex < 3; sideIndex++) {
                try {
                    new Triangle(sides1[sideIndex], sides2[sideIndex], sides3[sideIndex]);
                    valid++;
                }
                catch (IllegalArgumentException e) {
                    // not important
                }
            }
        }

        assertEquals(1838, valid);
    }
}
