package pl.samouczekprogramisty.szs.aoc2016.day17;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VaultExplorerTest {
    @Test
    public void shouldSolveGivenPuzzle1() {
        String path = new VaultExplorer("ihgpwlah").findShortestPath();
        assertEquals("DDRRRD", path);
    }

    @Test
    public void shouldSolveGivenPuzzle2() {
        String path = new VaultExplorer("kglvqrro").findShortestPath();
        assertEquals("DDUDRLRRUDRD", path);
    }

    @Test
    public void shouldSolveGivenPuzzle3() {
        String path = new VaultExplorer("ulqzkmiv").findShortestPath();
        assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", path);
    }

    @Test
    public void shouldFindLongestPath1() {
        String path = new VaultExplorer("ihgpwlah").findLongestPath();
        assertEquals(370, path.length());
    }

    @Test
    public void shouldFindLongestPath2() {
        String path = new VaultExplorer("kglvqrro").findLongestPath();
        assertEquals(492, path.length());
    }

    @Test
    public void shouldFindLongestPath3() {
        String path = new VaultExplorer("ulqzkmiv").findLongestPath();
        assertEquals(830, path.length());
    }
}