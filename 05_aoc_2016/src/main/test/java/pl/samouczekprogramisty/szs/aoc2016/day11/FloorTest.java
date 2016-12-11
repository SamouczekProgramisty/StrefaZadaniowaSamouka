package pl.samouczekprogramisty.szs.aoc2016.day11;

import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.day11.solution.Move;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class FloorTest {

    public static final Generator GA = new Generator("A");
    public static final Generator GB = new Generator("B");
    public static final Generator GC = new Generator("C");
    public static final Microchip MA = new Microchip("A");
    public static final Microchip MB = new Microchip("B");
    public static final Microchip MC = new Microchip("C");

    @Test
    public void shouldReturnOnlyPossibleMoves1() {
        Floor floor = new Floor(GA, MA, GB, MB, GC, MC);
        assertEquals(Arrays.asList(m(MA), m(MB), m(MC), m(MA, GA), m(MB, GB), m(MC, GC), m(MA, MB), m(MA, MC), m(MB, MC)), floor.listPossibleMoves());
    }

    @Test
    public void shouldReturnOnlyPossibleMoves2() {
        Floor floor = new Floor(GA, GB, GC, MC);
        assertEquals(Arrays.asList(m(GA), m(GB), m(MC), m(MC, GC), m(GA, GB)), floor.listPossibleMoves());
    }

    @Test
    public void shouldReturnOnlyPossibleMoves3() {
        Floor floor = new Floor(MA, MB, MC);
        assertEquals(Arrays.asList(m(MA), m(MB), m(MC), m(MA, MB), m(MA, MC), m(MB, MC)), floor.listPossibleMoves());
    }

    @Test
    public void shouldReturnOnlyPossibleMoves4() {
        Floor floor = new Floor(MA, GA);
        assertEquals(Arrays.asList(m(GA), m(MA), m(MA, GA)), floor.listPossibleMoves());
    }

    @Test
    public void shouldValidateThatFloorIsValid() {
        try {
            new Floor(MA, MB, GA);
            fail("Error should be thrown!");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Microchips [B] doesn't have generators within [A]!", e.getMessage());
        }
    }

    @Test
    public void shouldValidateThatFloorIsValid2() {
        try {
            new Floor(MA, MB, MC, GA, GB);
            fail("Error should be thrown!");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Microchips [C] doesn't have generators within [A, B]!", e.getMessage());
        }
    }

    private static Move m(ElectronicDevice device) {
        return m(device, null);
    }

    private static Move m(ElectronicDevice device1, ElectronicDevice device2) {
        return new Move(device1, device2);
    }
}