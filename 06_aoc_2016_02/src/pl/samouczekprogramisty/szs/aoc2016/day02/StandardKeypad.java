package pl.samouczekprogramisty.szs.aoc2016.day02;


public class StandardKeypad extends Keypad<StandardKeypad> {
    private static final int MAX_KEYPAD_INDEX = 2;
    private static final String[][] KEYPAD = new String[][] {
            new String[] {"7", "8", "9"},
            new String[] {"4", "5", "6"},
            new String[] {"1", "2", "3"}
    };

    public StandardKeypad() {
        this(new ButtonCoordinates(1, 1));
    }

    public StandardKeypad(ButtonCoordinates currentPosition) {
        super(currentPosition);
    }

    @Override
    protected String[][] getKeypad() {
        return KEYPAD;
    }

    @Override
    protected int getMaxKeypadIndex() {
        return MAX_KEYPAD_INDEX;
    }

    @Override
    protected StandardKeypad instantiate(ButtonCoordinates newPosition) {
        return new StandardKeypad(newPosition);
    }
}
