package pl.samouczekprogramisty.szs.aoc2016.day02;

public class FancyKeypad extends Keypad<FancyKeypad> {
    public static final int MAX_KEYPAD_INDEX = 4;
    public static final String[][] KEYPAD = new String[][] {
            new String[] {"-", "-", "D", "-", "-"},
            new String[] {"-", "A", "B", "C", "-"},
            new String[] {"5", "6", "7", "8", "9"},
            new String[] {"-", "2", "3", "4", "-"},
            new String[] {"-", "-", "1", "-", "-"},
    };

    public FancyKeypad() {
        this(new ButtonCoordinates(0, 2));
    }

    public FancyKeypad(ButtonCoordinates position) {
        super(position);
    }

    @Override
    protected int getMaxKeypadIndex() {
        return MAX_KEYPAD_INDEX;
    }

    @Override
    protected String[][] getKeypad() {
        return KEYPAD;
    }

    @Override
    protected FancyKeypad instantiate(ButtonCoordinates newPosition) {
        return new FancyKeypad(newPosition);
    }

    @Override
    protected ButtonCoordinates normalizeNewPosition(ButtonCoordinates startingPosition, ButtonCoordinates endingPosition) {
        ButtonCoordinates newPosition = super.normalizeNewPosition(startingPosition, endingPosition);
        if ("-".equals(getDigit(newPosition))) {
            return startingPosition;
        }
        return newPosition;
    }
}
