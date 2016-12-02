package pl.samouczekprogramisty.szs.aoc2016.day01;

import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.day01.Orientation;

import static org.junit.Assert.*;

public class OrientationTest {
    @Test
    public void shouldBeAbleToTurnLeft() {
        assertEquals(Orientation.WEST, Orientation.NORTH.turn(Orientation.Turn.LEFT));
        assertEquals(Orientation.NORTH, Orientation.EAST.turn(Orientation.Turn.LEFT));
        assertEquals(Orientation.EAST, Orientation.SOUTH.turn(Orientation.Turn.LEFT));
        assertEquals(Orientation.SOUTH, Orientation.WEST.turn(Orientation.Turn.LEFT));
    }

    @Test
    public void shouldBeAbleToTurnRight() {
        assertEquals(Orientation.EAST, Orientation.NORTH.turn(Orientation.Turn.RIGHT));
        assertEquals(Orientation.SOUTH, Orientation.EAST.turn(Orientation.Turn.RIGHT));
        assertEquals(Orientation.WEST, Orientation.SOUTH.turn(Orientation.Turn.RIGHT));
        assertEquals(Orientation.NORTH, Orientation.WEST.turn(Orientation.Turn.RIGHT));
    }
}