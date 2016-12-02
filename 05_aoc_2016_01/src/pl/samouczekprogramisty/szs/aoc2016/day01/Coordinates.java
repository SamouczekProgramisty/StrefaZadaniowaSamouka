package pl.samouczekprogramisty.szs.aoc2016.day01;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Coordinates {

    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinates move(Orientation orientation, int numberOfSteps) {
        List<Coordinates> moveWithTrail = moveWithTrail(orientation, numberOfSteps);
        int finalCoordinatesIndex = moveWithTrail.size() - 1;
        return moveWithTrail.get(finalCoordinatesIndex);
    }

    public List<Coordinates> moveWithTrail(Orientation orientation, int numberOfSteps) {
        List<Coordinates> trail = new LinkedList<>();
        Orientation.MoveVector moveVector = orientation.getMoveVector();
        for (int step = 1; step <= numberOfSteps; step++) {
            trail.add(
                new Coordinates(
                    x + moveVector.getX() * step,
                    y + moveVector.getY() * step
                )
            );
        }
        return trail;
    }

    public int distance(Coordinates other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
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
}
