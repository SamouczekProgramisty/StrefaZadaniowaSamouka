package pl.samouczekprogramisty.szs.aoc2016.day17;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VaultExplorer {
    private static final int VAULT_SIZE = 4;

    private final String salt;
    private final MessageDigest digest;

    private final static int UP = 0;
    private final static int DOWN = 1;
    private final static int LEFT = 2;
    private final static int RIGHT = 3;

    private static final Set<Character> DOOR_OPEN = new HashSet<>(Arrays.asList('b', 'c', 'd', 'e', 'f'));

    public VaultExplorer(String salt) {
        this.salt = salt;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    private static class Position {
        public final int x;
        public final int y;
        public final String path;

        public Position(int x, int y, String path) {
            this.x = x;
            this.y = y;
            this.path = path;
        }

        public List<Position> getAdjacentPositions() {
            List<Position> adjacentPositions = new ArrayList<>(4);
            return adjacentPositions;
        }

        @Override
        public String toString() {
            return "(" + x  + ", " + y + ")";
        }
    }

    public String findShortestPath() {
        Deque<Position> pathsToCheck = new ArrayDeque<>();
        Deque<Position> nextIteration = new ArrayDeque<>();
        pathsToCheck.add(new Position(0, 0, salt));

        while (pathsToCheck.size() > 0) {
            while (pathsToCheck.size() > 0) {
                Position position = pathsToCheck.pop();

                boolean reachedDestination = position.x == VAULT_SIZE - 1 && position.y == VAULT_SIZE - 1;
                if (reachedDestination) {
                    return position.path.substring(salt.length());
                }

                addAdjacentPositions(nextIteration, position);
            }
            pathsToCheck = nextIteration;
            nextIteration = new ArrayDeque<>();
        }

        throw new IllegalStateException("There is no exit!");
    }


    public String findLongestPath() {
        Deque<Position> pathsToCheck = new ArrayDeque<>();
        Deque<Position> nextIteration = new ArrayDeque<>();
        pathsToCheck.add(new Position(0, 0, salt));

        String longestPath = null;

        while (pathsToCheck.size() > 0) {
            while (pathsToCheck.size() > 0) {
                Position position = pathsToCheck.pop();

                boolean reachedDestination = position.x == VAULT_SIZE - 1 && position.y == VAULT_SIZE - 1;
                if (reachedDestination) {
                    longestPath = position.path.substring(salt.length());
                    continue;
                }

                addAdjacentPositions(nextIteration, position);
            }
            pathsToCheck = nextIteration;
            nextIteration = new ArrayDeque<>();
        }

        return longestPath;
    }

    private void addAdjacentPositions(Deque<Position> nextIteration, Position position) {
        int x = position.x;
        int y = position.y;
        String path = position.path;
        char[] characters = getDoorCodes(position);

        if (DOOR_OPEN.contains(characters[UP]) && y > 0) {
            nextIteration.add(new Position(x, y - 1, path + "U"));
        }
        if (DOOR_OPEN.contains(characters[DOWN]) && y < VAULT_SIZE - 1) {
            nextIteration.add(new Position(x, y + 1, path + "D"));
        }
        if (DOOR_OPEN.contains(characters[RIGHT]) && x < VAULT_SIZE - 1) {
            nextIteration.add(new Position(x + 1, y, path + "R"));
        }
        if (DOOR_OPEN.contains(characters[LEFT]) && x > 0) {
            nextIteration.add(new Position(x - 1, y, path + "L"));
        }
    }

    private char[] getDoorCodes(Position adjacentPosition) {
        digest.reset();
        digest.update(adjacentPosition.path.getBytes());
        String md5Sum = String.format("%032x", new BigInteger(1, digest.digest()));
        return md5Sum.toCharArray();
    }
}
