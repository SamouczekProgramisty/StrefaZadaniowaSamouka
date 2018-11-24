package pl.samouczekprogramisty.szs.aoc2016.day03;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleTest {
    @Test
    public void shouldBeAbleToInstantiateTriangle() {
        Triangle triangle = new Triangle(5, 4, 3);
        assertNotNull(triangle);
    }

    @Test
    public void shouldntAllowForCreatingIllegalTriangle() {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(2, 3, 5));
    }
}
