package pl.samouczekprogramisty.szs.aoc2016.day09;

import org.junit.jupiter.api.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldSolvePuzzle() {
        StringBuilder sb = new StringBuilder();
        for (String line : InputFileReader.readFileAsLines("day09_input.txt")) {
            sb.append(line);
        }
        assertEquals(112830, Decompressor.decompressV1(sb.toString()).length());
        assertEquals(10931789799L, Decompressor.decompressV2Lenght(sb.toString()));
    }
}
