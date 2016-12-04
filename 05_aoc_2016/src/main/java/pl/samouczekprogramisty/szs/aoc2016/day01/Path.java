package pl.samouczekprogramisty.szs.aoc2016.day01;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Path {
    private final List<Coordinates> path = new LinkedList<>();
    private final List<Coordinates> detailedPath = new LinkedList<>();
    private final Coordinates startingPoint;
    private Coordinates currentPoint;
    private Orientation orientation;

    private Path(Coordinates startingPoint, Orientation orientation) {
        path.add(startingPoint);
        currentPoint = startingPoint;
        this.startingPoint = startingPoint;
        this.orientation = orientation;
    }

    public void turnAndMove(Orientation.Turn where, int numberOfSteps) {
        orientation = orientation.turn(where);

        List<Coordinates> moveWithTrail = currentPoint.moveWithTrail(orientation, numberOfSteps);
        int finalCoordinatesIndex = moveWithTrail.size() - 1;
        currentPoint = moveWithTrail.get(finalCoordinatesIndex);

        path.add(currentPoint);
        detailedPath.addAll(moveWithTrail);
    }

    public static Path parse(String pathToParse) {
        Path path = new Path(new Coordinates(0, 0), Orientation.NORTH);
        if (pathToParse.length() == 0) {
            return path;
        }

        for(String move : pathToParse.split(", ")) {
            Orientation.Turn turn = Orientation.Turn.parse(move.substring(0, 1));
            int steps = Integer.parseInt(move.substring(1));
            path.turnAndMove(turn, steps);
        }

        return path;
    }

    public Coordinates getCurrentPoint() {
        return currentPoint;
    }

    public Coordinates getStartingPoint() {
        return startingPoint;
    }

    public int getDistance() {
        return startingPoint.distance(currentPoint);
    }

    public Coordinates getFirstCoordinateVisitedTwice() {
        Set<Coordinates> alreadyVisited = new HashSet<>();
        for(Coordinates point : detailedPath) {
            if (alreadyVisited.contains(point)) {
                return point;
            }
            alreadyVisited.add(point);
        }
        System.out.println(detailedPath);
        System.out.println(alreadyVisited);
        return null;
    }
}
