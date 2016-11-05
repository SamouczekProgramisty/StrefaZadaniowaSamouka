package pl.samouczekprogramisty.szs.gameoflife;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {
    private final Cell[][] board;

    private static class BordVisualisation {
        private static final String BOARD_VERTICAL_BORDER = "|";
        private static final String BOARD_HORIZONTAL_BORDER = "-";
        private static final String BOARD_CORNER = "+";

        public static String toString(Board b) {
            Cell[][] board = b.board;
            StringBuilder builder = new StringBuilder();
            builder.append(createTopBottomLine(board.length));
            builder.append(System.lineSeparator());
            for(int rowIndex = 1; rowIndex <= board.length; rowIndex++) {
                Cell[] row = board[board.length - rowIndex];
                builder.append(BOARD_VERTICAL_BORDER);
                for(Cell cell : row) {
                    builder.append(cell.toSimpleString());
                }
                builder.append(BOARD_VERTICAL_BORDER);
                builder.append(System.lineSeparator());
            }
            builder.append(createTopBottomLine(board.length));
            return builder.toString();
        }

        private static String createTopBottomLine(int size) {
            StringBuilder line = new StringBuilder();
            line.append(BOARD_CORNER);
            for(int i = 0; i < size; i++) {
                line.append(BOARD_HORIZONTAL_BORDER);
            }
            line.append(BOARD_CORNER);
            return line.toString();
        }
    }

    private static class Coordinates {
        private final int rowIndex;
        private final int columnIndex;

        public Coordinates(int columnIndex, int rowIndex) {
            this.columnIndex = columnIndex;
            this.rowIndex = rowIndex;
        }
    }

    public Board(int size, Cell... cells) {
        if (size < 3) {
            throw new IllegalArgumentException(String.format("Size %d is illegal!", size));
        }
        this.board = new Cell[size][size];

        for (Coordinates coordinates : iterateOverCells()) {
            setCell(Cell.dead(coordinates.columnIndex, coordinates.rowIndex));
        }

        for(Cell cell : cells) {
            setCell(cell);
        }
    }

    private Board(Cell[][] state) {
        board = new Cell[state.length][];
        for (int index = 0; index < state.length; index++) {
            board[index] = Arrays.copyOf(state[index], state[index].length);
        }
    }

    @Override
    public String toString() {
        return BordVisualisation.toString(this);
    }

    public Board nextGeneration() {
        Board nextGeneration = new Board(board);

        for (Coordinates coordinates : iterateOverCells()) {
            Cell cell = getCell(coordinates.columnIndex, coordinates.rowIndex);
            nextGeneration.setCell(cell.nextGeneration(this));
        }

        return nextGeneration;
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
