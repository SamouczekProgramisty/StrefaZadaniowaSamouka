package pl.samouczekprogramisty.szs.gameoflife;

import pl.samouczekprogramisty.szs.gameoflife.ui.Console;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {

    // TODO it's just a step to have proper visualisation code!
    // FIXME This property musn't be public!
    public final Cell[][] board;
    private final Console visualisation;

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
        return visualisation.boardToString();
    }

    public Board(int size, Cell... cells) {
        if (size < 3) {
            throw new IllegalArgumentException(String.format("Size %d is illegal!", size));
        }
        this.board = new Cell[size][size];
        this.visualisation = new Console(this);

        for (Coordinates coordinates : iterateOverCells()) {
            setCell(Cell.dead(coordinates.columnIndex, coordinates.rowIndex));
        }

        for(Cell cell : cells) {
            setCell(cell);
        }
    }

    private Board(Cell[][] state) {
        board = new Cell[state.length][];
        visualisation = new Console(this);

        for (int index = 0; index < state.length; index++) {
            board[index] = Arrays.copyOf(state[index], state[index].length);
        }
    }

    public Board nextGeneration() {
        Board nextGeneration = new Board(board);

        for (Cell cell : getCells()) {
            nextGeneration.setCell(cell.nextGeneration(this));
        }

        return nextGeneration;
    }

    public int getSize() {
        return board.length;
    }

    public Cell getCell(int columnIndex, int rowIndex) {
        columnIndex = normalizeIndex(columnIndex);
        rowIndex = normalizeIndex(rowIndex);
        return board[rowIndex][columnIndex];
    }

    public List<Cell> getCells() {
        List<Cell> boardsCells = new LinkedList<>();

        for (Coordinates coordinates : iterateOverCells()) {
            boardsCells.add(getCell(coordinates.columnIndex, coordinates.rowIndex));
        }

        return boardsCells;
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
