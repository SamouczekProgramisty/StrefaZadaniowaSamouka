package pl.samouczekprogramisty.szs.aoc2016.day14;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldSolvePuzzleStep1() {
        KeyFinder keyFinder = new KeyFinder("yjdafjpo");
        assertEquals(25427, keyFinder.getIndexOfKey(64));
    }

    @Test
    @Ignore
    public void shouldSolvePuzzleStep2() {
        KeyFinder keyFinder = new KeyFinder("yjdafjpo");
        assertEquals(22045, keyFinder.getIndexOfEnhancedKey(64));
    }
}
