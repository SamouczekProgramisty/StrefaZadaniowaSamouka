package pl.samouczekprogramisty.szs.aoc2016.day02;

import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldBeAbleToSolvePuzzle() {
        StringBuilder joinedLines = new StringBuilder();
        List<String> lines = InputFileReader.readFileAsLines("day02_input.txt");
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            boolean isLastLine = lineIndex == lines.size() - 1;
            joinedLines.append(lines.get(lineIndex));
            if (!isLastLine) {
                joinedLines.append(System.lineSeparator());
            }
        }
        assertEquals("45973", new StandardKeypad().parseCode(joinedLines.toString()));
        assertEquals("27CA4", new FancyKeypad().parseCode(joinedLines.toString()));
    }
}
