package pl.samouczekprogramisty.szs.aoc2016.day03;


import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {
    @Test
    public void shouldBeAbleToInstantiateTriangle() {
        Triangle triangle = new Triangle(5, 4, 3);
        Assert.assertNotNull(triangle);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldntAllowForCreatingIllegalTriangle() {
        new Triangle(2, 3, 5);
    }
}
