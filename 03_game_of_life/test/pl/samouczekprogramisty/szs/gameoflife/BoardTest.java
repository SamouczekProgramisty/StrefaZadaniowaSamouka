package pl.samouczekprogramisty.szs.gameoflife;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

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

}