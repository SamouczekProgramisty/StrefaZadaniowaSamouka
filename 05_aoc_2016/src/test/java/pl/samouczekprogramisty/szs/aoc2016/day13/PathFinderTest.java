package pl.samouczekprogramisty.szs.aoc2016.day13;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PathFinderTest {
    @Test
    public void shouldSolveExample() {
        Maze.Coordinates destination = new Maze.Coordinates(7, 4);
        int magicNumber = 10;
        assertEquals(Integer.valueOf(11), new PathFinder(magicNumber).findPath(destination));
    }
}