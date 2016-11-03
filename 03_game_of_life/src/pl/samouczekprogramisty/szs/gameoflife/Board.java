package pl.samouczekprogramisty.szs.gameoflife;


//Any live cell with fewer than two live neighbours dies, as if caused by under-population.

//Any live cell with two or three live neighbours lives on to the next generation.

//Any live cell with more than three live neighbours dies, as if by over-population.

//Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.


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
            for(Cell[] row : board) {
                builder.append(BOARD_VERTICAL_BORDER);
                for(Cell cell : row) {
                    builder.append(cell);
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

    public Board(int size, Cell... cells) {
        if (size < 1) {
            throw new IllegalArgumentException(String.format("Size %d is illegal!", size));
        }
        this.board = new Cell[size][];
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            this.board[rowIndex] = new Cell[size];
            for (int columnIndex = 0; columnIndex < size; columnIndex++) {
                this.board[rowIndex][columnIndex] = Cell.dead(rowIndex, columnIndex);
            }
        }
        for(Cell cell : cells) {
            this.board[cell.getRowIndex()][cell.getColumnIndex()] = cell;
        }
    }

    @Override
    public String toString() {
        return BordVisualisation.toString(this);
    }
}
