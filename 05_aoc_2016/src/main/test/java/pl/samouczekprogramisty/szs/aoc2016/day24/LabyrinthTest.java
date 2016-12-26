package pl.samouczekprogramisty.szs.aoc2016.day24;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LabyrinthTest {

    @Test
    public void shouldSolveSamplePuzzle() {
        String labyrinth = "###########" + System.lineSeparator() +
                "#0.1.....2#" + System.lineSeparator() +
                "#.#######.#" + System.lineSeparator() +
                "#4.......3#" + System.lineSeparator() +
                "###########";

        Labyrinth parsedLabyrinth = new Labyrinth(LabyrinthParser.parse(labyrinth));
        assertEquals(14, parsedLabyrinth.findShortestPath());
    }

    @Test
    public void shouldSolveSlightlyTrickierProblem() {
        String labyrinth = "#########" + System.lineSeparator() +
                           "#.1..2#.#" + System.lineSeparator() +
                           "###6#.#3#" + System.lineSeparator() +
                           "#5#.0...#" + System.lineSeparator() +
                           "#4..#.#.#" + System.lineSeparator() +
                           "#########";
        Labyrinth parsedLabyrinth = new Labyrinth(LabyrinthParser.parse(labyrinth));
        assertEquals(19, parsedLabyrinth.findShortestPath());
    }
}