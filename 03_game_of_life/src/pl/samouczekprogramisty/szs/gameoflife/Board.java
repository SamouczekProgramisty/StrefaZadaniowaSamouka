package pl.samouczekprogramisty.szs.gameoflife;


//Any live cell with fewer than two live neighbours dies, as if caused by under-population.

//Any live cell with two or three live neighbours lives on to the next generation.

//Any live cell with more than three live neighbours dies, as if by over-population.
//Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.


public class Board {
    public static final String BOARD_VERTICAL_BORDER = "|";
    public static final String BOARD_HORIZONTAL_BORDER = "-";
    public static final String BOARD_CORNER = "+";

    public static final String DEAD_CELL = " ";
    private final int size;

    public Board(int size) {
        if (size < 1) {
            throw new IllegalArgumentException(String.format("Size %d is illegal!", size));
        }
        this.size = size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        createTopBottomLine(builder);
        builder.append(System.lineSeparator());
        for(int rowNumber = 0; rowNumber < size; rowNumber++) {
            builder.append(BOARD_VERTICAL_BORDER);
            for(int columnNumber = 0; columnNumber < size; columnNumber++) {
                builder.append(DEAD_CELL);
            }
            builder.append(BOARD_VERTICAL_BORDER);
            builder.append(System.lineSeparator());
        }
        createTopBottomLine(builder);
        return builder.toString();
    }

    private void createTopBottomLine(StringBuilder builder) {
        builder.append(BOARD_CORNER);
        for(int i = 0; i < size; i++) {
            builder.append(BOARD_HORIZONTAL_BORDER);
        }
        builder.append(BOARD_CORNER);
    }
}
