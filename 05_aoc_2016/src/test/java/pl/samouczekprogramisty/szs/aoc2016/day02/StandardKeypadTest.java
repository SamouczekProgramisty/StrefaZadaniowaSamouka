package pl.samouczekprogramisty.szs.aoc2016.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardKeypadTest {
    @Test
    public void shouldRetunValidStartButton() {
        assertEquals("5", new StandardKeypad().getCurrentDigit());
    }

    @Test
    public void shouldBeAbleToMove() {
        assertEquals("2", new StandardKeypad().move("U").getCurrentDigit());
    }

    @Test
    public void shouldBeAbleToMoveByTwo() {
        assertEquals("1", new StandardKeypad().move("UL").getCurrentDigit());
    }

    @Test
    public void shouldBeAbleToMoveToCorner() {
        assertEquals("1", new StandardKeypad().move("ULL").getCurrentDigit());
        assertEquals("1", new StandardKeypad().move("ULU").getCurrentDigit());

        assertEquals("3", new StandardKeypad().move("URR").getCurrentDigit());
        assertEquals("3", new StandardKeypad().move("URU").getCurrentDigit());

        assertEquals("7", new StandardKeypad().move("DLL").getCurrentDigit());
        assertEquals("7", new StandardKeypad().move("DLD").getCurrentDigit());

        assertEquals("9", new StandardKeypad().move("DRR").getCurrentDigit());
        assertEquals("9", new StandardKeypad().move("DRD").getCurrentDigit());
    }

    @Test
    public void shouldBeAbleToParseCode() {
        String moveSequence = "ULL" + System.lineSeparator() +
                              "RRDDD" + System.lineSeparator() +
                              "LURDL" + System.lineSeparator() +
                              "UUUUD";
        assertEquals("1985", new StandardKeypad().parseCode(moveSequence));
    }
}