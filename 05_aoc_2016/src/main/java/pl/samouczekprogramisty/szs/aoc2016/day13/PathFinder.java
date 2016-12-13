package pl.samouczekprogramisty.szs.aoc2016.day13;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class PathFinder {
    private final Maze maze;

    public PathFinder(int magicNumber) {
        maze = new Maze(magicNumber);
    }

    public Integer findPath(Maze.Coordinates destination) {
        Map<Maze.Coordinates, Integer> path = new HashMap<>();
        Maze.Coordinates startingPoint = new Maze.Coordinates(1, 1);
        traverseMaze(path, startingPoint, new IterationDecision() {
            @Override
            public boolean shouldStop(Integer currentPathLenght) {
                return path.containsKey(destination);
            }
        });

        return path.get(destination);
    }

    public Integer howManyReachableWithMoves(int maxNumberOfSteps) {
        Map<Maze.Coordinates, Integer> path = new HashMap<>();
        Maze.Coordinates startingPoint = new Maze.Coordinates(1, 1);
        traverseMaze(path, startingPoint, new IterationDecision() {
            @Override
            public boolean shouldStop(Integer currentPathLenght) {
                return currentPathLenght == maxNumberOfSteps;
            }
        });

        int tilesReachableInXSteps = 0;
        for (Map.Entry<Maze.Coordinates, Integer> entry : path.entrySet()) {
            Integer stepsRequired = entry.getValue();
            if (stepsRequired >= 0 && stepsRequired <= maxNumberOfSteps) {
                tilesReachableInXSteps++;
            }
        }
        return tilesReachableInXSteps;
    }

    private interface IterationDecision {
        boolean shouldStop(Integer currentPathLenght);
    }

    private void traverseMaze(Map<Maze.Coordinates, Integer> path, Maze.Coordinates startingPoint, IterationDecision iterationDecision) {
        Integer currentPathLenght = 0;
        path.put(startingPoint, currentPathLenght);

        Deque<Maze.Coordinates> coordinatesToCheck = new ArrayDeque<>();
        coordinatesToCheck.add(startingPoint);

        Deque<Maze.Coordinates> newCoordinatesToCheck = new ArrayDeque<>();

        do {
            while (coordinatesToCheck.size() > 0) {
                Maze.Coordinates currentPoint = coordinatesToCheck.pop();
                currentPathLenght = path.get(currentPoint);
                for (Maze.Coordinates nextPoint : currentPoint.adjacentCoordinates()) {
                    if (!path.containsKey(nextPoint)) {
                        if (maze.isWall(nextPoint)) {
                            path.put(nextPoint, Integer.MIN_VALUE);
                        }
                        else {
                            path.put(nextPoint, currentPathLenght + 1);
                            newCoordinatesToCheck.add(nextPoint);
                        }
                    }
                }
            }
            coordinatesToCheck = newCoordinatesToCheck;
            newCoordinatesToCheck = new ArrayDeque<>();
            if (iterationDecision.shouldStop(currentPathLenght)) {
                break;
            }
        }
        while (coordinatesToCheck.size() > 0);
    }
}
