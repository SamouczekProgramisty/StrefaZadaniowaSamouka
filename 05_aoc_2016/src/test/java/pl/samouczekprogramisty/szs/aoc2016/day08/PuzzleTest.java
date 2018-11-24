package pl.samouczekprogramisty.szs.aoc2016.day08;

import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldSolvePuzzle() {
        Display display = new Display(50, 6);
        List<String> commands = InputFileReader.readFileAsLines("day08_input.txt");
        display = display.apply(commands);
        String expected =
                "#..#.###...##....##.####.#....###...##..####.####." + System.lineSeparator() +
                "#..#.#..#.#..#....#.#....#....#..#.#..#.#.......#." + System.lineSeparator() +
                "#..#.#..#.#..#....#.###..#....###..#....###....#.." + System.lineSeparator() +
                "#..#.###..#..#....#.#....#....#..#.#....#.....#..." + System.lineSeparator() +
                "#..#.#....#..#.#..#.#....#....#..#.#..#.#....#...." + System.lineSeparator() +
                ".##..#.....##...##..#....####.###...##..####.####." + System.lineSeparator();

        assertEquals(116, display.countPixelsOn());
        assertEquals(expected, display.toString());
    }
}
