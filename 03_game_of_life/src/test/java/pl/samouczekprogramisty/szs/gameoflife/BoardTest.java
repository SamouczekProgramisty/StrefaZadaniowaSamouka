package pl.samouczekprogramisty.szs.gameoflife;

import org.junit.jupiter.api.Test;
import pl.samouczekprogramisty.szs.gameoflife.ui.Console;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void shouldBeAbleToCreateNewBoard() {
        assertNotNull(new Board(10));
    }

    @Test
    void shouldBeAbleToPrintSmallBoard() {
        String expected = "+---+" + System.lineSeparator() +
                          "|   |" + System.lineSeparator() +
                          "|   |" + System.lineSeparator() +
                          "|   |" + System.lineSeparator() +
                          "+---+" ;
        Board board = new Board(3);
        assertEquals(expected, new Console(board).boardToString());
    }

    @Test
    void shouldntAllowToCreateIllegalBoard() {
        assertThrows(IllegalArgumentException.class, () -> new Board(0));
    }

    @Test
    void shouldntAllowToCreateIllegalBoardWithNegativeSize() {
        assertThrows(IllegalArgumentException.class, () -> new Board(-10));
    }

    @Test
    void shouldBeAbleToCreateBoardWithCells() {
        String boardVisualisation = "+---+" + System.lineSeparator() +
                                    "|   |" + System.lineSeparator() +
                                    "|   |" + System.lineSeparator() +
                                    "|o  |" + System.lineSeparator() +
                                    "+---+";
        Board board = new Board(3, Cell.live(0, 0));
        assertEquals(boardVisualisation, board.toString());
    }

    @Test
    void shouldBeAbleToCreateBiggerBoard() {
        String boardVisualisation = "+-------+" + System.lineSeparator() +
                                    "|       |" + System.lineSeparator() +
                                    "|       |" + System.lineSeparator() +
                                    "|       |" + System.lineSeparator() +
                                    "|       |" + System.lineSeparator() +
                                    "|ooo    |" + System.lineSeparator() +
                                    "|       |" + System.lineSeparator() +
                                    "|       |" + System.lineSeparator() +
                                    "+-------+";
        Board board = new Board(7, Cell.live(0, 2), Cell.live(1, 2), Cell.live(2, 2));
        assertEquals(boardVisualisation, board.toString());
    }

    @Test
    void shouldBeAbleToCreateNextGeneration() {
        String boardVisualisation = "+---+" + System.lineSeparator() +
                                    "|   |" + System.lineSeparator() +
                                    "| o |" + System.lineSeparator() +
                                    "|   |" + System.lineSeparator() +
                                    "+---+";
        Board board = new Board(3, Cell.live(1, 1));
        assertEquals(boardVisualisation, board.toString());

        String nextGeneration = "+---+" + System.lineSeparator() +
                                "|   |" + System.lineSeparator() +
                                "|   |" + System.lineSeparator() +
                                "|   |" + System.lineSeparator() +
                                "+---+";
        Board boardNextGeneration = board.nextGeneration();
        assertEquals(nextGeneration, boardNextGeneration.toString());
    }

    @Test
    void shouldBeImmutable() {
        String boardVisualisation = "+---+" + System.lineSeparator() +
                                    "| o |" + System.lineSeparator() +
                                    "| o |" + System.lineSeparator() +
                                    "| o |" + System.lineSeparator() +
                                    "+---+";
        Board board = new Board(3, Cell.live(1, 0), Cell.live(1, 1), Cell.live(1, 2));
        assertEquals(boardVisualisation, board.toString());

        board.nextGeneration();
        assertEquals(boardVisualisation, new Console(board, System.out).boardToString());
    }

    @Test
    void shouldBeAbleToProvideNextGenerationWithPeriod() {
        String boardVisualisation = "+----+" + System.lineSeparator() +
                                    "|    |" + System.lineSeparator() +
                                    "| o  |" + System.lineSeparator() +
                                    "| o  |" + System.lineSeparator() +
                                    "| o  |" + System.lineSeparator() +
                                    "+----+";
        Board board = new Board(4, Cell.live(1, 0), Cell.live(1, 1), Cell.live(1, 2));
        assertEquals(boardVisualisation, board.toString());

        String expected = "+----+" + System.lineSeparator() +
                          "|    |" + System.lineSeparator() +
                          "|    |" + System.lineSeparator() +
                          "|ooo |" + System.lineSeparator() +
                          "|    |" + System.lineSeparator() +
                          "+----+";
        Board boardNextGeneration = board.nextGeneration();
        assertEquals(expected, boardNextGeneration.toString());
    }

    @Test
    void shouldBeAbleToAnimateGlider() {
        String boardVisualisation = "+-----+" + System.lineSeparator() +
                                    "| o   |" + System.lineSeparator() +
                                    "|  o  |" + System.lineSeparator() +
                                    "|ooo  |" + System.lineSeparator() +
                                    "|     |" + System.lineSeparator() +
                                    "|     |" + System.lineSeparator() +
                                    "+-----+";
        Board board = new Board(5, Cell.live(0, 2), Cell.live(1, 2), Cell.live(2, 2), Cell.live(2, 3), Cell.live(1, 4));
        assertEquals(boardVisualisation, board.toString());

        // https://en.wikipedia.org/wiki/Glider_(Conway%27s_Life)
        // glider's period is 4
        String expected = "+-----+" + System.lineSeparator() +
                          "|     |" + System.lineSeparator() +
                          "|  o  |" + System.lineSeparator() +
                          "|   o |" + System.lineSeparator() +
                          "| ooo |" + System.lineSeparator() +
                          "|     |" + System.lineSeparator() +
                          "+-----+";
        board = board.nextGeneration().nextGeneration().nextGeneration().nextGeneration();
        assertEquals(expected, board.toString());
    }
}