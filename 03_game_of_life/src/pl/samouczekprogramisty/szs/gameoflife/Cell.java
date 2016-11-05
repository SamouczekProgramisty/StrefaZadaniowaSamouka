package pl.samouczekprogramisty.szs.gameoflife;

public class Cell {
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

    public Cell flip() {
        return new Cell(state.flip(), columnIndex, rowIndex);
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

    public CellState getState() {
        return state;
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
