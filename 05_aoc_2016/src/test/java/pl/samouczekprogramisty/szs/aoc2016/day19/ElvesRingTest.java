package pl.samouczekprogramisty.szs.aoc2016.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElvesRingTest {
    @Test
    public void shouldPointRightElfNumber() {
        assertEquals(3, new ElvesRing(5).findLastThief());
    }

    @Test
    public void shouldPointRightElfNumberWithDiffentWayOfPickingThief() {
        assertEquals(2, new ElvesRing(5).findLastThiefInFont());
    }
}