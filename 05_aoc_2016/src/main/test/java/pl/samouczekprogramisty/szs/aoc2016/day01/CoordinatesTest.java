package pl.samouczekprogramisty.szs.aoc2016.day01;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinatesTest {

    @Test
    public void shouldBeAbleToCreateCoordinates() {
        Coordinates coordinates = new Coordinates(1, 2);
        assertEquals(1, coordinates.getX());
        assertEquals(2, coordinates.getY());
    }

    @Test
    public void shouldntModifieOriginalCoordinatesDuringMove() {
        Coordinates coordinates = new Coordinates(0, 1);
        Coordinates newCoordinates = coordinates.move(Orientation.NORTH, 2);
        assertNotSame(coordinates, newCoordinates);
        assertEquals(coordinates.getX(), 0);
        assertEquals(coordinates.getY(), 1);
    }

    @Test
    public void shouldBeAbleToMove() {
        Coordinates coordinates = new Coordinates(0, 1);
        Coordinates newCoordinates = coordinates.move(Orientation.NORTH, 2);
        assertEquals(newCoordinates.getX(), 0);
        assertEquals(newCoordinates.getY(), 3);
    }

}