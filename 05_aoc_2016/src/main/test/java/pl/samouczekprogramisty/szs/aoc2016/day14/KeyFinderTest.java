package pl.samouczekprogramisty.szs.aoc2016.day14;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KeyFinderTest {
    @Test
    public void shouldPassProvidedTestCase() {
        KeyFinder keyFinder = new KeyFinder("abc");
        assertEquals(22728, keyFinder.getIndexOfKey(64));
    }

    @Test
    @Ignore
    public void shouldPassProvidedTestCase2() {
        KeyFinder keyFinder = new KeyFinder("abc");
        assertEquals(22551, keyFinder.getIndexOfEnhancedKey(64));
    }
}