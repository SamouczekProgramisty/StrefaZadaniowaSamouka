package pl.samouczekprogramisty.szs.aoc2016.day14;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldSolvePuzzleStep1() {
        KeyFinder keyFinder = new KeyFinder("yjdafjpo");
        assertEquals(25427, keyFinder.getIndexOfKey(64));
    }

    @Test
    @Disabled("takse ~2m to finish")
    public void shouldSolvePuzzleStep2() {
        KeyFinder keyFinder = new KeyFinder("yjdafjpo");
        assertEquals(22045, keyFinder.getIndexOfEnhancedKey(64));
    }
}
