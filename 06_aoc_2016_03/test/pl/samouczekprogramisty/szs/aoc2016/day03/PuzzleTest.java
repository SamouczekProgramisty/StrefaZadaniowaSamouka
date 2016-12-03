package pl.samouczekprogramisty.szs.aoc2016.day03;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PuzzleTest {
    private static final Pattern pattern = Pattern.compile("(\\d+) +(\\d+) +(\\d+)");

    @Test
    public void shouldBeAbleToSolvePuzzle() throws IOException {
        int valid = 0;
        int invalid = 0;

        try(BufferedReader input = new BufferedReader(new FileReader(new File("test" + File.separator + "input.txt")))) {
            String line;
            while ((line = input.readLine()) != null) {
                int[] sides = parse(line);
                try {
                    new Triangle(sides[0], sides[1], sides[2]);
                    valid++;
                }
                catch (IllegalArgumentException e) {
                    invalid++;
                }
            }
        }

        System.out.println(valid);
        System.out.println(invalid);
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
        int invalid = 0;

        try(BufferedReader input = new BufferedReader(new FileReader(new File("test" + File.separator + "input.txt")))) {
            String firstLine;
            while ((firstLine = input.readLine()) != null) {
                int[] sides1 = parse(firstLine);
                int[] sides2 = parse(input.readLine());
                int[] sides3 = parse(input.readLine());
                for (int i = 0; i < 3; i++) {
                    try {
                        new Triangle(sides1[i], sides2[i], sides3[i]);
                        valid++;
                    }
                    catch (IllegalArgumentException e) {
                        invalid++;
                    }
                }
            }
        }

        System.out.println(valid);
        System.out.println(invalid);
    }
}
