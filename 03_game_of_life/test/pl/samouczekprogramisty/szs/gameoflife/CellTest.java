package pl.samouczekprogramisty.szs.gameoflife;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {
    @Test
    public void shouldBeAbleToFlipCellState() {
        Cell cell = Cell.dead(0, 0);
        Cell flipped = cell.flip();
        assertEquals(Cell.CellState.LIVE, flipped.getState());
    }
}