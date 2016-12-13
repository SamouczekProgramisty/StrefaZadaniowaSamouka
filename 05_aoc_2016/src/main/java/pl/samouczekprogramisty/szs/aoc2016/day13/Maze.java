package pl.samouczekprogramisty.szs.aoc2016.day13;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Maze {
    private final int magicNumber;
    private final Map<Coordinates, Boolean> maze = new HashMap<>();

    public Maze(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public static final class Coordinates {
        public final int x;
        public final int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Coordinates that = (Coordinates) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public List<Coordinates> adjacentCoordinates() {
            List<Coordinates> returnedValue = new LinkedList<>();
            if (y - 1 >= 0) {
                returnedValue.add(new Coordinates(x, y - 1));
            }

            returnedValue.add(new Coordinates(x + 1, y));
            returnedValue.add(new Coordinates(x,y + 1));

            if (x - 1 >= 0) {
                returnedValue.add(new Coordinates(x - 1, y));
            }

            return returnedValue;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public boolean isWall(Coordinates coordinates) {
        if (maze.containsKey(coordinates)) {
            return maze.get(coordinates);
        }

        boolean isWall = decideIfItIsWall(coordinates);
        maze.put(coordinates, isWall);

        return isWall;
    }

    private boolean decideIfItIsWall(Coordinates coordinates) {
        int x = coordinates.x;
        int y = coordinates.y;

        int number = x * x + 3 * x + 2 * x * y + y + y * y + magicNumber;
        int numberOfOnes = 0;
        while (number > 0) {
            if (number % 2 == 1) {
                numberOfOnes++;
            }
            number = number >> 1;
        }
        return numberOfOnes % 2 != 0;
    }

    public String toString(int maxX, int maxY) {
        StringBuilder maze = new StringBuilder();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (isWall(new Coordinates(x, y))) {
                    maze.append('#');
                }
                else {
                    maze.append('.');
                }
            }
            maze.append(System.lineSeparator());
        }
        return maze.toString();
    }
}
