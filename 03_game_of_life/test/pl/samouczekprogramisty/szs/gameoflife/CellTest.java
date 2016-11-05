package pl.samouczekprogramisty.szs.gameoflife;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void shouldBeAbleToReturnNeighbours() {
        Cell cell00 = Cell.dead(0, 0);
        Cell cell01 = Cell.dead(0, 1);
        Cell cell02 = Cell.dead(0, 2);
        Cell cell10 = Cell.dead(1, 0);
        Cell cell11 = Cell.dead(1, 1);
        Cell cell12 = Cell.dead(1, 2);
        Cell cell20 = Cell.dead(2, 0);
        Cell cell21 = Cell.dead(2, 1);
        Cell cell22 = Cell.dead(2, 2);

        Board board = new Board(3, cell00, cell01, cell02, cell10, cell11, cell12, cell20, cell21, cell22);

        List<Cell> neighbours = cell11.getNeighbours(board);
        List<Cell> expected = Arrays.asList(cell00, cell01, cell02, cell10, cell12, cell20, cell21, cell22);

        assertEquals(expected, neighbours);
    }
}