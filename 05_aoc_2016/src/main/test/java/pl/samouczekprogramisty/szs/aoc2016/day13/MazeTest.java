package pl.samouczekprogramisty.szs.aoc2016.day13;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class MazeTest {
    @Test
    public void shouldReturnValidAdjacentCoordinates() {
        List<Maze.Coordinates> adjacentCoordinates = new Maze.Coordinates(1, 1).adjacentCoordinates();

        List<Maze.Coordinates> expected = Arrays.asList(
                new Maze.Coordinates(1, 0),
                new Maze.Coordinates(2, 1),
                new Maze.Coordinates(1, 2),
                new Maze.Coordinates(0, 1)
        );
        assertEquals(expected, adjacentCoordinates);
    }

    @Test
    public void shouldDrawMaze() {
        String expectedMaze = ".#.####.##" + System.lineSeparator() +
                "..#..#...#" + System.lineSeparator() +
                "#....##..." + System.lineSeparator() +
                "###.#.###." + System.lineSeparator() +
                ".##..#..#." + System.lineSeparator() +
                "..##....#." + System.lineSeparator() +
                "#...##.###" + System.lineSeparator();

        Maze maze = new Maze(10);
        assertEquals(expectedMaze, maze.toString(10, 7));
    }
}