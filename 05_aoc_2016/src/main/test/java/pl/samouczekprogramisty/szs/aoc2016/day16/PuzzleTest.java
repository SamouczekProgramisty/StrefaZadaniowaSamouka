package pl.samouczekprogramisty.szs.aoc2016.day16;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldSolvePuzzle() {
        assertEquals("10111110010110110", new DragonChecksum("10011111011011001", 272).getChecksum());
        assertEquals("01101100001100100", new DragonChecksum("10011111011011001", 35651584).getChecksum());
    }
}
