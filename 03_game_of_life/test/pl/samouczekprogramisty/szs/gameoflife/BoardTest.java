package pl.samouczekprogramisty.szs.gameoflife;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BoardTest {
    @Test
    public void shouldBeAbleToCreateNewBoard() {
        assertNotNull(new Board(10));
    }

    @Test
    public void shouldBeAbleToPrintSmallBoard() {
        String expected = "+-+" + System.lineSeparator() + "| |" + System.lineSeparator() + "+-+" ;
        assertEquals(expected, new Board(1).toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldntAllowToCreateIllegalBoard() {
        new Board(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldntAllowToCreateIllegalBoardWithNegativeSize() {
        new Board(-10);
    }

    @Test
    public void shouldBeAbleToCreateBoardWithCells() {
        String boardVisualisation = "+-+" + System.lineSeparator() +
                                    "|o|" + System.lineSeparator() +
                                    "+-+";
        Board board = new Board(1, Cell.live(0, 0));
        assertEquals(boardVisualisation, board.toString());
    }
}