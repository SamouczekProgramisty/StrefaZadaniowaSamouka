package pl.samouczekprogramisty.szs.aoc2016.day02;

import java.util.LinkedList;
import java.util.List;

public abstract class Keypad<K extends Keypad> {
    protected ButtonCoordinates currentPosition;

    public static class ButtonCoordinates {
        private final int x;
        private final int y;

        public ButtonCoordinates(int x, int y) {
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

    public Keypad() {
        this(new ButtonCoordinates(1, 1));
    }

    public Keypad(ButtonCoordinates currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getCurrentDigit() {
        return getDigit(currentPosition);
    }

    protected final String getDigit(ButtonCoordinates coordinates) {
        return getKeypad()[coordinates.getY()][coordinates.getX()];
    }

    public K move(String moveSequence) {
        K returnValue = (K) this;
        for (String moveLetter : moveSequence.split("")) {
            KeypadMove move = KeypadMove.parse(moveLetter);
            ButtonCoordinates digitPosition = returnValue.currentPosition;
            ButtonCoordinates newPosition = normalizeNewPosition(
                    digitPosition,
                    new ButtonCoordinates(
                        digitPosition.getX() + move.getXMove(),
                        digitPosition.getY() + move.getYMove()
                    )
            );
            returnValue = instantiate(newPosition);
        }
        return returnValue;
    }

    public String parseCode(String numberSequences) {
        Keypad keypad = this;
        List<String> digits = new LinkedList<>();
        for (String moveSequence : numberSequences.split(System.lineSeparator())) {
            keypad = keypad.move(moveSequence);
            digits.add(keypad.getCurrentDigit());
        }
        return joinDigits(digits);
    }

    private String joinDigits(List<String> digits) {
        StringBuilder sb = new StringBuilder();
        for(String digit : digits) {
            sb.append(digit);
        }
        return sb.toString();
    }

    protected ButtonCoordinates normalizeNewPosition(ButtonCoordinates startingPosition, ButtonCoordinates endingPosition) {
        int x = endingPosition.getX();
        int y = endingPosition.getY();

        if (x < 0) {
            x = 0;
        }
        else if (x > getMaxKeypadIndex()) {
            x = getMaxKeypadIndex();
        }
        if (y < 0) {
            y = 0;
        }
        else if (y > getMaxKeypadIndex()) {
            y = getMaxKeypadIndex();
        }
        return new ButtonCoordinates(x, y);
    }

    protected abstract int getMaxKeypadIndex();

    protected abstract String[][] getKeypad();

    protected abstract K instantiate(ButtonCoordinates newPosition);
}
