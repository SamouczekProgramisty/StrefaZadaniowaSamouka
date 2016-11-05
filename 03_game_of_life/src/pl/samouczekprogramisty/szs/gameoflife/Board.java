package pl.samouczekprogramisty.szs.gameoflife;

import pl.samouczekprogramisty.szs.gameoflife.ui.Console;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {

    // TODO it's just a step to have proper visualisation code!
    // FIXME This property musn't be public!
    public final Cell[][] board;
    private final int size;

    private static class Coordinates {
        private final int rowIndex;
        private final int columnIndex;

        public Coordinates(int columnIndex, int rowIndex) {
            this.columnIndex = columnIndex;
            this.rowIndex = rowIndex;
        }
    }

    @Override
    public String toString() {
        return new Console(this).boardToString();
    }

    public Board(int size, Cell... cells) {
        if (size < 3) {
            throw new IllegalArgumentException(String.format("Size %d is illegal!", size));
        }
        this.board = new Cell[size][size];
        this.size = size;

        for (Coordinates coordinates : iterateOverCells()) {
            setCell(Cell.dead(coordinates.columnIndex, coordinates.rowIndex));
        }

        for(Cell cell : cells) {
            setCell(cell);
        }
    }

    private Board(Cell[][] state) {
        board = new Cell[state.length][];
        size = state.length;
        for (int index = 0; index < state.length; index++) {
            board[index] = Arrays.copyOf(state[index], state[index].length);
        }
    }

    public Board nextGeneration() {
        Board nextGeneration = new Board(board);

        for (Coordinates coordinates : iterateOverCells()) {
            Cell cell = getCell(coordinates.columnIndex, coordinates.rowIndex);
            nextGeneration.setCell(cell.nextGeneration(this));
        }

        return nextGeneration;
    }

    public int getSize() {
        return size;
    }

    public Cell getCell(int columnIndex, int rowIndex) {
        columnIndex = normalizeIndex(columnIndex);
        rowIndex = normalizeIndex(rowIndex);
        return board[rowIndex][columnIndex];
    }

    private void setCell(Cell cell) {
        board[cell.getRowIndex()][cell.getColumnIndex()] = cell;
    }

    private int normalizeIndex(int index) {
        int size = board.length;
        // This magic line makes the board "infinite".
        // It makes that the "neighbour" of the first row/column is the last row/column.
        return (index + size) % size;
    }

    private List<Coordinates> iterateOverCells() {
        List<Coordinates> returnValue = new LinkedList<>();
        for(int rowIndex = 0; rowIndex < board.length; rowIndex++) {
            for(int columnIndex = 0; columnIndex < board.length; columnIndex++) {
                returnValue.add(new Coordinates(columnIndex, rowIndex));
            }
        }
        return returnValue;
    }
}
