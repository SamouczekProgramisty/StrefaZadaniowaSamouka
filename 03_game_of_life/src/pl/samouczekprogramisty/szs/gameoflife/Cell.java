package pl.samouczekprogramisty.szs.gameoflife;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Cell {
    private static String TO_STRING_FORMAT = "Cell{%s, (%d, %d)}";

    private final CellState state;
    private final int rowIndex;
    private final int columnIndex;

    private Cell(CellState state, int columnIndex, int rowIndex) {
        this.state = state;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public static Cell live(int columnIndex, int rowIndex) {
        return new Cell(CellState.LIVE, columnIndex, rowIndex);
    }

    public static Cell dead(int columnIndex, int rowIndex) {
        return new Cell(CellState.DEAD, columnIndex, rowIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return Objects.equals(rowIndex, cell.rowIndex) &&
                Objects.equals(columnIndex, cell.columnIndex) &&
                Objects.equals(state, cell.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, rowIndex, columnIndex);
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, state, columnIndex, rowIndex);
    }

    public String toSimpleString() {
        return state.getRepresentation();
    }

    public Cell flip() {
        return new Cell(state.flip(), columnIndex, rowIndex);
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public CellState getState() {
        return state;
    }

    public List<Cell> getNeighbours(Board board) {
        List<Cell> neighbours = new LinkedList<>();
        for (int rowDelta = -1; rowDelta <= 1; rowDelta++) {
            for (int columnDelata = -1; columnDelata <= 1; columnDelata++) {
                if (rowDelta == 0 && columnDelata == 0) {
                    continue;
                }
                neighbours.add(board.getCell(columnIndex + columnDelata, rowIndex + rowDelta));
            }
        }
        return neighbours;
    }

    public boolean isLive() {
        return state.isLive();
    }

    public Cell nextGeneration(Board board) {
        int liveNeighbours = 0;
        for(Cell cell : getNeighbours(board)) {
            liveNeighbours += cell.isLive() ? 1 : 0;
        }

        if (isLive()) {
            switch (liveNeighbours) {
                // Any live cell with two or three live neighbours lives on to the next generation.
                case 2:
                case 3:
                    return this;
                // Any live cell with fewer than two live neighbours dies, as if caused by under-population.
                // Any live cell with more than three live neighbours dies, as if by over-population.
                default:
                    return flip();
            }
        }
        // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
        if (liveNeighbours == 3) {
            return flip();
        }
        return this;
    }

    public enum CellState {
        LIVE("o"),
        DEAD(" ");

        private final String representation;

        CellState(String representation) {
            this.representation = representation;
        }

        public String getRepresentation() {
            return representation;
        }

        public CellState flip() {
            if (this == LIVE) {
                return DEAD;
            }
            return LIVE;
        }

        public boolean isLive() {
            return this == LIVE;
        }
    }
}
