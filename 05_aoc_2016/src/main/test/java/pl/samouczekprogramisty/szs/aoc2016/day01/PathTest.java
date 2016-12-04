package pl.samouczekprogramisty.szs.aoc2016.day01;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PathTest {
    @Test
    public void shouldBeAbleToParseEmptyPathSpecification() {
        Path path = Path.parse("");
        Assert.assertEquals(new Coordinates(0, 0), path.getCurrentPoint());
    }

    @Test
    public void shouldBeAbleToParseSimplePath1() {
        Path path = Path.parse("R2, L3");
        assertEquals(new Coordinates(2, 3), path.getCurrentPoint());
    }

    @Test
    public void shouldBeAbleToParseSimplePath2() {
        Path path = Path.parse("R2, R2, R2");
        assertEquals(new Coordinates(0, -2), path.getCurrentPoint());
    }

    @Test
    public void shouldBeAbleToParseSimplePath3() {
        Path path = Path.parse("R5, L5, R5, R3");
        assertEquals(new Coordinates(10, 2), path.getCurrentPoint());
    }

    @Test
    public void shouldBeAbleToGetDistanceForSimplePath1() {
        Path path = Path.parse("R2, L3");
        assertEquals(5, path.getDistance());
    }

    @Test
    public void shouldBeAbleToGetDistanceForSimplePath2() {
        Path path = Path.parse("R2, R2, R2");
        assertEquals(2, path.getDistance());
    }

    @Test
    public void shouldBeAbleToGetDistanceForSimplePath3() {
        Path path = Path.parse("R5, L5, R5, R3");
        assertEquals(12, path.getDistance());
    }

    @Test
    public void shouldBeAbleToSpotFirstVisitedTwicePoint() {
        Path path = Path.parse("R8, R4, R4, R8");
        assertEquals(new Coordinates(4, 0), path.getFirstCoordinateVisitedTwice());
    }
}