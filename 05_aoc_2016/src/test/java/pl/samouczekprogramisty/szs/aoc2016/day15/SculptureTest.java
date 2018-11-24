package pl.samouczekprogramisty.szs.aoc2016.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SculptureTest {
    @Test
    public void shouldBeOkToThrowCapsuleAtCertainTime() {
        Sculpture sculpture = new Sculpture(
            new Disc(5, 4, 1),
            new Disc(2, 1, 2)
        );
        assertEquals(5, sculpture.canFallThroughAtTime());
    }
}