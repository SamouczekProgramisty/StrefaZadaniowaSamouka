package pl.samouczekprogramisty.szs.aoc2016.day08;


import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Display {
    private final static Pattern REACT_PATTERN = Pattern.compile("rect (\\d+)x(\\d+)$");
    private final static Pattern ROTATE_COLUMN_PATTERN = Pattern.compile("rotate column x=(\\d+) by (\\d+)");
    private final static Pattern ROTATE_ROW_PATTERN = Pattern.compile("rotate row y=(\\d+) by (\\d+)");

    private final boolean[][] display;
    private final int sizeX;
    private final int sizeY;

    public Display(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        display = new boolean[sizeY][sizeX];
    }

    public Display apply(List<String> commands) {
        Display returnedDisplay = this;

        for(String command : commands) {
            Matcher matcher = REACT_PATTERN.matcher(command);
            if (matcher.matches()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                returnedDisplay = returnedDisplay.rect(x, y);
                continue;
            }
            matcher = ROTATE_ROW_PATTERN.matcher(command);
            if (matcher.matches()) {
                int row = Integer.parseInt(matcher.group(1));
                int by = Integer.parseInt(matcher.group(2));
                returnedDisplay = returnedDisplay.rotateRow(row, by);
                continue;
            }
            matcher = ROTATE_COLUMN_PATTERN.matcher(command);
            if (matcher.matches()) {
                int column = Integer.parseInt(matcher.group(1));
                int by = Integer.parseInt(matcher.group(2));
                returnedDisplay = returnedDisplay.rotateColumn(column, by);
                continue;
            }
            throw new IllegalStateException(command);
        }

        return returnedDisplay;
    }

    private Display(boolean[][] display) {
        this.display = display;
        sizeY = display.length;
        if (sizeY != 0) {
            sizeX = display[0].length;
        } else {
            sizeX = 0;
        }
    }

    public Display rect(int x, int y) {
        boolean[][] newDisplay = copyDisplay();

        for (int rowIndex = 0; rowIndex < y; rowIndex++) {
            for(int columnIndex = 0; columnIndex < x; columnIndex++) {
                newDisplay[rowIndex][columnIndex] = true;
            }
        }

        return new Display(newDisplay);
    }

    public Display rotateColumn(int column, int by) {
        boolean[][] newDisplay = copyDisplay();

        for (int rotationIndex = 0; rotationIndex < sizeY; rotationIndex++) {
            int rotated = (rotationIndex - by + sizeY) % sizeY;
            newDisplay[rotationIndex][column] = display[rotated][column];
        }

        return new Display(newDisplay);
    }

    public Display rotateRow(int row, int by) {
        boolean[][] newDisplay = copyDisplay();

        for (int rotationIndex = 0; rotationIndex < sizeX; rotationIndex++) {
            int rotated = (rotationIndex - by + sizeX) % sizeX;
            newDisplay[row][rotationIndex] = display[row][rotated];
        }

        return new Display(newDisplay);
    }

    private boolean[][] copyDisplay() {
        boolean[][] newDisplay = new boolean[sizeY][sizeX];
        for (int rowIndex = 0; rowIndex < sizeY; rowIndex++) {
            newDisplay[rowIndex] = Arrays.copyOf(display[rowIndex], sizeX);
        }
        return newDisplay;
    }

    @Override
    public String toString() {
        StringBuilder allRows = new StringBuilder(sizeX * sizeY);
        for (int rowIndex = 0; rowIndex < sizeY; rowIndex++) {
            StringBuilder row = new StringBuilder(sizeY);
            for (int columnIndex = 0; columnIndex < sizeX; columnIndex++) {
                boolean pixel = display[rowIndex][columnIndex];
                if (pixel) {
                    row.append("#");
                }
                else {
                    row.append(".");
                }
            }
            allRows.append(row);
            allRows.append(System.lineSeparator());
        }
        return allRows.toString();
    }

    public int countPixelsOn() {
        int pixelsOn = 0;
        for (int rowIndex = 0; rowIndex < sizeY; rowIndex++) {
            for (int columnIndex = 0; columnIndex < sizeX; columnIndex++) {
                boolean pixel = display[rowIndex][columnIndex];
                if (pixel) {
                    pixelsOn++;
                }
            }
        }
        return pixelsOn;
    }
}