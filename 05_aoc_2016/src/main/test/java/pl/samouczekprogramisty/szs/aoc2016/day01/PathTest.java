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

    @Test
    public void puzzleTest() {
        Path path = Path.parse("R3, L2, L2, R4, L1, R2, R3, R4, L2, R4, L2, L5, L1, R5, R2, R2, L1, R4, R1, L5, L3, R4, R3, R1, L1, L5, L4, L2, R5, L3, L4, R3, R1, L3, R1, L3, R3, L4, R2, R5, L190, R2, L3, R47, R4, L3, R78, L1, R3, R190, R4, L3, R4, R2, R5, R3, R4, R3, L1, L4, R3, L4, R1, L4, L5, R3, L3, L4, R1, R2, L4, L3, R3, R3, L2, L5, R1, L4, L1, R5, L5, R1, R5, L4, R2, L2, R1, L5, L4, R4, R4, R3, R2, R3, L1, R4, R5, L2, L5, L4, L1, R4, L4, R4, L4, R1, R5, L1, R1, L5, R5, R1, R1, L3, L1, R4, L1, L4, L4, L3, R1, R4, R1, R1, R2, L5, L2, R4, L1, R3, L5, L2, R5, L4, R5, L5, R3, R4, L3, L3, L2, R2, L5, L5, R3, R4, R3, R4, R3, R1");
        System.out.println(path.getDistance());
        System.out.println(path.getStartingPoint().distance(path.getFirstCoordinateVisitedTwice()));
    }
}