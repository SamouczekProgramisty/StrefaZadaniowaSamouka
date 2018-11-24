package pl.samouczekprogramisty.szs.aoc2016.day18;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldSolvePuzzle() {
        Sapper sapper = new Sapper(".^^^.^.^^^.^.......^^.^^^^.^^^^..^^^^^.^.^^^..^^.^.^^..^.^..^^...^.^^.^^^...^^.^.^^^..^^^^.....^....");
        sapper.getLine(40);
        assertEquals(2013, sapper.getSafeTilesCount());
        sapper.getLine(400000);
        assertEquals(20006289, sapper.getSafeTilesCount());
    }
}
