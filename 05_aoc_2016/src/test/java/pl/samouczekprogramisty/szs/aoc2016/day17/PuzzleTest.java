package pl.samouczekprogramisty.szs.aoc2016.day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldSolvePuzzle() {
        String path = new VaultExplorer("udskfozm").findShortestPath();
        assertEquals("DDRLRRUDDR", path);
    }
    @Test
    public void shouldSolvePuzzlePartTwo() {
        String path = new VaultExplorer("udskfozm").findLongestPath();
        assertEquals(556, path.length());
    }
}
