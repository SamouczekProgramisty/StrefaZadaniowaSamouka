package pl.samouczekprogramisty.szs.aoc2016.day13;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PuzzleTest {
    @Test
    public void shouldSolvePuzzle() {
        Maze.Coordinates destination = new Maze.Coordinates(31, 39);
        int magicNumber = 1362;
        PathFinder pathFinder = new PathFinder(magicNumber);

        assertEquals(Integer.valueOf(82), pathFinder.findPath(destination));
        assertEquals(Integer.valueOf(138), pathFinder.howManyReachableWithMoves(50));
    }
}
