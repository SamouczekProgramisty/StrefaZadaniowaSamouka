package pl.samouczekprogramisty.szs.aoc2016.day24;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Labyrinth {

    public static final int NUMBER_SYSTEM_BASE = 10;
    private final Vertex startingVertex;
    private final Map<Integer, Vertex> vertices = new HashMap<>();
    private final Map<Vertex, Set<Edge>> edges = new HashMap<>();

    public enum CoordinateType {
        WALL('#'),
        PASSAGE('.'),
        SPECIAL_PLACE('\0');

        private final char symbol;

        CoordinateType(char symbol) {
            this.symbol = symbol;
        }

        public static CoordinateType parse(char character) {
            if (character == WALL.symbol) {
                return WALL;
            }
            if (character == PASSAGE.symbol) {
                return PASSAGE;
            }
            if (Character.isDigit(character)) {
                return SPECIAL_PLACE;
            }
            throw new IllegalArgumentException("" + character);
        }
    }

    public Labyrinth(char[][] labyrinth) {
        startingVertex = findStartingPoint(labyrinth);
        buildGraph(labyrinth);
    }

    private void buildGraph(char[][] labyrinth) {
        Deque<Vertex> verticesToExplore = new ArrayDeque<>();
        verticesToExplore.add(startingVertex);

        vertices.put(0, startingVertex);

        while (!verticesToExplore.isEmpty()) {
            Vertex startVertex = verticesToExplore.pop();
            for (Edge edge : findEdgesFromVertex(labyrinth, startVertex)) {
                Vertex endVertex = edge.getEnd();
                if (!vertices.containsKey(endVertex.getSpotNumber())) {
                    verticesToExplore.add(endVertex);
                    vertices.put(endVertex.getSpotNumber(), endVertex);
                }
                addEdge(edge);
            }
        }
    }

    private List<Edge> findEdgesFromVertex(char[][] labyrinth, Vertex startVertex) {
        List<Edge> edges = new LinkedList<>();

        int maxColumnIndex = labyrinth.length - 1;
        int maxRowIndex = labyrinth[0].length - 1;

        Set<Coordinates> visited = new HashSet<>();

        Deque<Coordinates> coordinatesToVisit = new ArrayDeque<>();
        Set<Coordinates> nextIteration;
        coordinatesToVisit.add(startVertex.getCoordinates());

        int edgeLength = 1;
        while (!coordinatesToVisit.isEmpty()) {
            nextIteration = new HashSet<>();
            while (!coordinatesToVisit.isEmpty()) {
                Coordinates currentPoint = coordinatesToVisit.pop();
                visited.add(currentPoint);
                for (Coordinates adjacent : currentPoint.adjacent(maxColumnIndex, maxRowIndex)) {
                    if (visited.contains(adjacent)) {
                        continue;
                    }
                    char symbol = labyrinth[adjacent.x][adjacent.y];
                    switch (CoordinateType.parse(symbol)) {
                        case PASSAGE:
                            nextIteration.add(adjacent);
                            break;
                        case WALL:
                            break;
                        case SPECIAL_PLACE:
                            Integer specialPlace = Character.digit(symbol, NUMBER_SYSTEM_BASE);
                            visited.add(adjacent);
                            Vertex end = new Vertex(adjacent, specialPlace);
                            edges.add(new Edge(startVertex, end, edgeLength));
                            break;
                    }
                }
            }
            edgeLength += 1;
            coordinatesToVisit = new ArrayDeque<>(nextIteration);
        }

        return edges;
    }

    private void addEdge(Edge edge) {
        for (Vertex vertex : edge.getVertices()) {
            if (!edges.containsKey(vertex)) {
                edges.put(vertex, new HashSet<>());
            }
            edges.get(vertex).add(edge);
        }
    }

    private static Vertex findStartingPoint(char[][] labyrinth) {
        for (int columnIndex = 0; columnIndex < labyrinth.length; columnIndex++) {
            char[] column = labyrinth[columnIndex];

            for (int rowIndex = 0; rowIndex < column.length; rowIndex++) {
                if (column[rowIndex] == '0') {
                    return new Vertex(
                        new Coordinates(columnIndex, rowIndex),
                        0
                    );
                }
            }
        }
        throw new IllegalStateException("Missing starting point!");
    }

    private class VertexPair {
        public final Set<Vertex> vertices;

        public VertexPair(Vertex vertex1, Vertex vertex2) {
            vertices = new HashSet<>();
            vertices.add(vertex1);
            vertices.add(vertex2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            VertexPair that = (VertexPair) o;
            return Objects.equals(vertices, that.vertices);
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertices);
        }
    }

    public int findShortestPath() {
        Map<VertexPair, Integer> directDistances = computeDistancesBetweenVertices();

        int shortestPath = Integer.MAX_VALUE;
        for (List<Vertex> permutation : permutations()) {
            int permutationDistance = 0;
            Vertex start = vertices.get(0);
            for (Vertex end : permutation) {
                permutationDistance += directDistances.get(new VertexPair(start, end));
                start = end;
            }
            if (permutationDistance < shortestPath) {
                shortestPath = permutationDistance;
            }
        }

        return shortestPath;
    }

    public int findShortestPathStartingAndEndingAt0() {
        Map<VertexPair, Integer> directDistances = computeDistancesBetweenVertices();

        int shortestPath = Integer.MAX_VALUE;

        for (List<Vertex> permutation : permutations()) {
            int permutationDistance = 0;
            Vertex start = vertices.get(0);
            for (Vertex end : permutation) {
                permutationDistance += directDistances.get(new VertexPair(start, end));
                start = end;
            }
            permutationDistance += directDistances.get(new VertexPair(start, vertices.get(0)));
            if (permutationDistance < shortestPath) {
                shortestPath = permutationDistance;
            }
        }

        return shortestPath;
    }

    private Map<VertexPair, Integer> computeDistancesBetweenVertices() {
        Map<VertexPair, Integer> directDistances = new HashMap<>();

        for (Vertex start : vertices.values()) {
            for (Vertex end : vertices.values()) {
                if (start.equals(end)) {
                    continue;
                }
                VertexPair pair = new VertexPair(start, end);
                if (!directDistances.containsKey(pair)) {
                    directDistances.put(pair, findShortestPathFromTo(start, end));
                }
            }
        }
        return directDistances;
    }


    private List<List<Vertex>> permutations() {
        List<List<Vertex>> permutations = new LinkedList<>();
        Set<Vertex> verticesToPermutate = new HashSet<>(vertices.values());
        verticesToPermutate.remove(vertices.get(0));
        permutations(permutations, Collections.emptyList(), verticesToPermutate);
        return permutations;
    }

    private static void permutations(List<List<Vertex>> permutations, List<Vertex> permutation, Set<Vertex> leftToPermutate) {
        int n = leftToPermutate.size();
        if (n == 0) {
            permutations.add(permutation);
        }
        else {
            for (Vertex v : leftToPermutate) {
                HashSet<Vertex> newLeftToPermutate = new HashSet<>(leftToPermutate);
                newLeftToPermutate.remove(v);

                List<Vertex> newPermutation = new LinkedList<>(permutation);
                newPermutation.add(v);

                permutations(permutations, newPermutation, newLeftToPermutate);
            }
        }
    }

    private int findShortestPathFromTo(Vertex pathStart, Vertex pathEnd) {
        List<Integer> distances = new ArrayList<>();
        List<Integer> paths = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>();
        visited.add(pathStart);
        findPaths(paths, 0, pathStart, pathEnd, visited);
        for (Integer distance : paths) {
            distances.add(distance);
        }
        Collections.sort(distances);
        return distances.get(0);
    }

    private void findPaths(List<Integer> paths, Integer currentLength, Vertex start, Vertex pathEnd, Set<Vertex> visited) {
        if (start.equals(pathEnd)) {
            paths.add(currentLength);
        }

        List<Edge> edgesToCheck = new ArrayList<>();
        for (Edge edge : edges.get(start)) {
            if (!visited.contains(edge.getEnd())) {
                edgesToCheck.add(edge);
            }
        }

        visited.add(start);

        for (Edge edge : edgesToCheck) {
            findPaths(paths, currentLength + edge.getNumberOfSteps(), edge.getEnd(), pathEnd, visited);
        }
    }
}