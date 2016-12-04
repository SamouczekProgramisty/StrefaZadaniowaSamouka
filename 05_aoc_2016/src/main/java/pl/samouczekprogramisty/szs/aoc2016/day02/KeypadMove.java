package pl.samouczekprogramisty.szs.aoc2016.day02;

public enum KeypadMove {
    UP(0, 1),
    RIGHT(1, 0),
    DOWN(0, -1),
    LEFT(-1, 0);

    private final int xMove;
    private final int yMove;

    KeypadMove(int xMove, int yMove) {
        this.xMove = xMove;
        this.yMove = yMove;
    }

    public static KeypadMove parse(String move) {
        switch (move) {
            case "U":
                return UP;
            case "R":
                return RIGHT;
            case "D":
                return DOWN;
            case "L":
                return LEFT;
            default:
                throw new IllegalArgumentException(move);
        }
    }

    public int getXMove() {
        return xMove;
    }

    public int getYMove() {
        return yMove;
    }
}
