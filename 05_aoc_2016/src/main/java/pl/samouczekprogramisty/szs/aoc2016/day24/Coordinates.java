package pl.samouczekprogramisty.szs.aoc2016.day24;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Coordinates {
    public final int x;
    public final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public List<Coordinates> adjacent(int maxColumnIndex, int maxRowIndex) {
        List<Coordinates> adjacent = new LinkedList<>();
        if (x > 0) {
            adjacent.add(new Coordinates(x - 1, y));
        }
        if (y > 0) {
            adjacent.add(new Coordinates(x, y - 1));
        }
        if (x < maxColumnIndex) {
            adjacent.add(new Coordinates(x + 1, y));
        }
        if (y < maxRowIndex) {
            adjacent.add(new Coordinates(x, y + 1));
        }

        return adjacent;
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
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}