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

    @Test
    public void shouldBeImmutable() {
        Cell cell = Cell.live(0, 0);
        assertNotSame(cell, cell.flip());
    }
}