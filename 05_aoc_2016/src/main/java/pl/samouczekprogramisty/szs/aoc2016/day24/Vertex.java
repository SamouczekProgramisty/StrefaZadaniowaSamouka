package pl.samouczekprogramisty.szs.aoc2016.day24;


import java.util.Objects;

public class Vertex {
    private final Integer spotNumber;
    private final Coordinates coordinates;

    public Vertex(Coordinates coordinates, Integer spotNumber) {
        this.coordinates = coordinates;
        this.spotNumber = spotNumber;
    }

    public Integer getSpotNumber() {
        return spotNumber;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vertex vertex = (Vertex) o;
        return Objects.equals(spotNumber, vertex.spotNumber) &&
                Objects.equals(coordinates, vertex.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotNumber, coordinates);
    }

    @Override
    public String toString() {
        return "V(" + spotNumber + ")";
    }
}
