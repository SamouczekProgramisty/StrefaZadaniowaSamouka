package pl.samouczekprogramisty.szs.aoc2016.day02;

import org.junit.Test;

import static org.junit.Assert.*;

public class FancyKeypadTest {
    @Test
    public void shouldBeAbleToInstantiateFancyKeypad() {
        FancyKeypad keypad = new FancyKeypad();
        assertEquals("5", keypad.getCurrentDigit());
    }

    @Test
    public void shouldntAllowToMoveOutsideOfTheKeypad() {
        FancyKeypad keypad = new FancyKeypad().move("U");
        assertEquals("5", keypad.getCurrentDigit());
    }

    @Test
    public void shouldStayAtC() {
        FancyKeypad keypad = new FancyKeypad(new Keypad.ButtonCoordinates(3, 1));
        assertEquals("C", keypad.getCurrentDigit());
        assertEquals("C", keypad.move("D").getCurrentDigit());
        assertEquals("C", keypad.move("R").getCurrentDigit());
    }

    @Test
    public void shouldBeAbleToParseCode() {
        String moveSequence = "ULL" + System.lineSeparator() +
                "RRDDD" + System.lineSeparator() +
                "LURDL" + System.lineSeparator() +
                "UUUUD";
        assertEquals("5DB3", new FancyKeypad().parseCode(moveSequence));
    }

    @Test
    public void shouldBeAbleToParseCode2() {
        FancyKeypad keypad = new FancyKeypad(new Keypad.ButtonCoordinates(2, 0));
        assertEquals("D", keypad.getCurrentDigit());
        assertEquals("B", keypad.move("LURDL").getCurrentDigit());
    }
}