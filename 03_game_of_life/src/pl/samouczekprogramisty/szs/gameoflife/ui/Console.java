package pl.samouczekprogramisty.szs.gameoflife.ui;

import pl.samouczekprogramisty.szs.gameoflife.Board;
import pl.samouczekprogramisty.szs.gameoflife.Cell;

import java.io.PrintStream;

public class Console {
    private static final String BOARD_VERTICAL_BORDER = "|";
    private static final String BOARD_HORIZONTAL_BORDER = "-";
    private static final String BOARD_CORNER = "+";

    private final Board board;
    private final PrintStream outputStream;

    public Console(Board board, PrintStream outputStream) {
        this.board = board;
        this.outputStream = outputStream;
    }

    public String boardToString() {
        Cell[][] board = this.board.board;
        StringBuilder builder = new StringBuilder();
        builder.append(createTopBottomLine(this.board.getSize()));
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
