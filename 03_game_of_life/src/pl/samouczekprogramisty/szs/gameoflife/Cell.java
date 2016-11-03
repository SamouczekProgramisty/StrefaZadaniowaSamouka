package pl.samouczekprogramisty.szs.gameoflife;

public class Cell {
    private final CellState state;
    private final int rowIndex;
    private final int columnIndex;

    public Cell(CellState state, int columnIndex, int rowIndex) {
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
    public String toString() {
        return state.getRepresentation();
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public enum CellState {
        LIVE("o"),
        DEAD(" ");

        private String representation;

        CellState(String representation) {
            this.representation = representation;
        }

        public String getRepresentation() {
            return representation;
        }
    }
}
