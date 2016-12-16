package pl.samouczekprogramisty.szs.aoc2016.day15;

import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;

import static org.junit.Assert.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldBeOkToThrowCapsuleAtCertainTime2() {
        Sculpture sculpture = Sculpture.createScrulpture(InputFileReader.readFileAsLines("day15_input.txt"));
        assertEquals(317371, sculpture.canFallThroughAtTime());
    }

    @Test
    public void shouldBeOkToThrowCapsuleAtCertainTime3() {
        Sculpture sculpture = new Sculpture(
            new Disc(17, 1, 1),
            new Disc(7, 0, 2),
            new Disc(19, 2, 3),
            new Disc(5, 0, 4),
            new Disc(3, 0, 5),
            new Disc(13, 5, 6),
            new Disc(11, 0, 7)
        );
        assertEquals(2080951, sculpture.canFallThroughAtTime());
    }
}
