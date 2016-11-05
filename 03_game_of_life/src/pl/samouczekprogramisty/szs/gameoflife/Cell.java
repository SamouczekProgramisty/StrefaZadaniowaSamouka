package pl.samouczekprogramisty.szs.gameoflife;

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

    public static Cell live(int columnIndex, int rowIndex) {
        return new Cell(CellState.LIVE, columnIndex, rowIndex);
    }

    public static Cell dead(int columnIndex, int rowIndex) {
        return new Cell(CellState.DEAD, columnIndex, rowIndex);
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
        return null;
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
    }
}
