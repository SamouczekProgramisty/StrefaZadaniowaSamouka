package pl.samouczekprogramisty.szs.aoc2016.day24;

import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;

import static org.junit.Assert.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldSolveSamplePuzzle1() {
        String labyrinth = InputFileReader.readFile("day24_input.txt");
        Labyrinth parsedLabyrinth = new Labyrinth(LabyrinthParser.parse(labyrinth));
        assertEquals(460, parsedLabyrinth.findShortestPath());

    }

    @Test
    public void shouldSolveSamplePuzzle2() {
        String labyrinth = InputFileReader.readFile("day24_input.txt");
        Labyrinth parsedLabyrinth = new Labyrinth(LabyrinthParser.parse(labyrinth));
        assertEquals(668, parsedLabyrinth.findShortestPathStartingAndEndingAt0());
    }
}
