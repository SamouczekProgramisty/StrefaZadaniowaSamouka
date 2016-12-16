package pl.samouczekprogramisty.szs.aoc2016.day15;

public class Disc {
    public final int numberOfPositions;
    public final int startingPosition;
    public final int discLevel;

    public Disc(int numberOfPositions, int startingPosition, int discLevel) {
        this.numberOfPositions = numberOfPositions;
        this.startingPosition = startingPosition;
        this.discLevel = discLevel;
    }

    public boolean canFallThroughAtTime(int time) {
        return (startingPosition + discLevel + 1 + time) % numberOfPositions == 0;
    }
}