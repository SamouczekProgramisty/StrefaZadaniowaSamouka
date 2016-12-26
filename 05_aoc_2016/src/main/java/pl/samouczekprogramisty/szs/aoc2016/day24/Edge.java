package pl.samouczekprogramisty.szs.aoc2016.day24;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Edge {
    private final int numberOfSteps;
    private final Set<Vertex> vertices;
    private final Vertex start;
    private final Vertex end;

    public Edge(Vertex start, Vertex end, int numberOfSteps) {
        vertices = new HashSet<>();
        vertices.add(start);
        vertices.add(end);
        this.start = start;
        this.end = end;
        this.numberOfSteps = numberOfSteps;
    }

    public Set<Vertex> getVertices() {
        return Collections.unmodifiableSet(vertices);
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Edge edge = (Edge) o;

        return Objects.equals(numberOfSteps, edge.numberOfSteps) &&
               Objects.equals(start, edge.start) &&
               Objects.equals(end, edge.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfSteps, start, end);
    }

    @Override
    public String toString() {
        return "(" + start.getSpotNumber() + "<->" + end.getSpotNumber() + ", " + numberOfSteps + ")";
    }
}
