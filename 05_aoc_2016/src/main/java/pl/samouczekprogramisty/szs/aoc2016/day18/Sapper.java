package pl.samouczekprogramisty.szs.aoc2016.day18;

import java.util.ArrayList;
import java.util.List;

public class Sapper {
    private static final char TRAP = '^';
    private static final char SAFE = '.';

    private final List<char[]> room = new ArrayList<>();

    public Sapper(String firstLine) {
        room.add(firstLine.toCharArray());
    }

    public String getLine(int lineNumber) {
        int lineIndex = lineNumber - 1;
        if (room.size() <= lineIndex) {
            generateLines(lineIndex);
        }
        return new String(room.get(lineIndex));
    }

    private void generateLines(int lineIndex) {
        for (int lastIndex = room.size() - 1; lastIndex < lineIndex; lastIndex++) {
            char[] line = room.get(lastIndex);
            room.add(buildNextLine(line));
        }
    }

    private char[] buildNextLine(char[] line) {
        char[] nextLine = new char[line.length];

        for (int tileIndex = 0; tileIndex < nextLine.length; tileIndex++) {
            char[] adjacentTiles = getAdjacentTiles(line, tileIndex);
            char nextTile = getNextTile(adjacentTiles);
            nextLine[tileIndex] = nextTile;
        }

        return nextLine;
    }

    private char getNextTile(char[] adjacentTiles) {
        char left = adjacentTiles[0];
        char center = adjacentTiles[1];
        char right = adjacentTiles[2];

        if (left == TRAP  && center == TRAP && right == SAFE) {
            return TRAP;
        }
        if (center == TRAP && right == TRAP && left == SAFE) {
            return TRAP;
        }
        if (left == TRAP && center == SAFE && right == SAFE) {
            return TRAP;
        }
        if (left == SAFE && center == SAFE && right == TRAP) {
            return TRAP;
        }
        return SAFE;
    }

    private char[] getAdjacentTiles(char[] line, int tileIndex) {
        char[] adjacentTiles = new char[3];
        if (tileIndex == 0) {
            adjacentTiles[0] = SAFE;
        }
        else {
            adjacentTiles[0] = line[tileIndex - 1];
        }
        adjacentTiles[1] = line[tileIndex];
        if (tileIndex == line.length - 1) {
            adjacentTiles[2] = SAFE;
        }
        else {
            adjacentTiles[2] = line[tileIndex + 1];
        }
        return adjacentTiles;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] line : room) {
            for (char tile : line) {
                sb.append(tile);
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public int getSafeTilesCount() {
        int safeTilesCount = 0;
        for (char[] line : room) {
            for (char tile : line) {
                if (tile == SAFE) {
                    safeTilesCount++;
                }
            }
        }
        return safeTilesCount;
    }
}
