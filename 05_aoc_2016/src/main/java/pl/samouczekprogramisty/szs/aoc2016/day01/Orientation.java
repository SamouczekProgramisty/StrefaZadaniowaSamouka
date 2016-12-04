package pl.samouczekprogramisty.szs.aoc2016.day01;


public enum Orientation {
    NORTH(new MoveVector(0, 1)),
    EAST(new MoveVector(1, 0)),
    SOUTH(new MoveVector(0, -1)),
    WEST(new MoveVector(-1, 0));

    private final MoveVector moveVector;

    public static class MoveVector {
        private final int x;
        private final int y;

        private MoveVector(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public enum Turn {
        LEFT,
        RIGHT;

        public static Turn parse(String turn) {
            if ("R".equals(turn)) {
                return RIGHT;
            }
            if ("L".equals(turn)) {
                return LEFT;
            }
            throw new IllegalArgumentException(turn);
        }
    }

    Orientation(MoveVector moveVector) {
        this.moveVector = moveVector;
    }

    public Orientation turn(Turn where) {
        int newOrientationIndex;
        int numberOfPossibleOrientations = Orientation.values().length;
        if (where == Turn.LEFT) {
            newOrientationIndex = (numberOfPossibleOrientations + this.ordinal() - 1) % numberOfPossibleOrientations;
        }
        else {
            newOrientationIndex = (this.ordinal() + 1) % numberOfPossibleOrientations;
        }
        return Orientation.values()[newOrientationIndex];
    }

    public MoveVector getMoveVector() {
        return moveVector;
    }
}
