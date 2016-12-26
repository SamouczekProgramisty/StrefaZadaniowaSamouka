package pl.samouczekprogramisty.szs.aoc2016.day24;

public class LabyrinthParser {
    private LabyrinthParser(){
    }

    public static char[][] parse(String labyrinth) {
        String[] rows = labyrinth.split(System.lineSeparator());
        int numberOfRows = rows.length;
        if (numberOfRows < 3) {
            throw new IllegalArgumentException(labyrinth);
        }
        int numberOfColumns = rows[0].length();
        char[][] parsedLabyrinth = new char[numberOfColumns][numberOfRows];

        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
            String row = rows[rowIndex];
            if (row.length() != numberOfColumns) {
                throw new IllegalArgumentException(labyrinth);
            }
            for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
                char symbol = row.charAt(columnIndex);
                // validation
                Labyrinth.CoordinateType.parse(symbol);
                parsedLabyrinth[columnIndex][rowIndex] = symbol;
            }
        }

        return parsedLabyrinth;
    }
}
