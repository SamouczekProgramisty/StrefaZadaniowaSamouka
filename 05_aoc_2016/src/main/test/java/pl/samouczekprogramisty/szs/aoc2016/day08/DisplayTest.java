package pl.samouczekprogramisty.szs.aoc2016.day08;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by mapi on 09.12.16.
 */
public class DisplayTest {

    @Test
    public void shouldParseRect() {
        Display display = new Display(7, 3);
        display = display.apply(Arrays.asList("rect 3x2"));

        String expected =
                "###...." + System.lineSeparator() +
                "###...." + System.lineSeparator() +
                "......." + System.lineSeparator();

        assertEquals(expected, display.toString());
    }

    @Test
    public void shouldParseRotateColumn() {
        Display display = new Display(7, 3);
        display = display.apply(Arrays.asList("rect 3x2", "rotate column x=1 by 1"));

        String expected =
                "#.#...." + System.lineSeparator() +
                "###...." + System.lineSeparator() +
                ".#....." + System.lineSeparator();

        assertEquals(expected, display.toString());
    }

    @Test
    public void shouldParseRotateRow() {
        Display display = new Display(7, 3);
        display = display.apply(Arrays.asList("rect 3x2", "rotate column x=1 by 1", "rotate row y=0 by 4"));

        String expected =
                "....#.#" + System.lineSeparator() +
                "###...." + System.lineSeparator() +
                ".#....." + System.lineSeparator();

        assertEquals(expected, display.toString());
    }

    @Test
    public void shouldHandleOverflow() {
        Display display = new Display(7, 3);
        display = display.apply(Arrays.asList("rect 3x2", "rotate column x=1 by 1", "rotate row y=0 by 4", "rotate column x=1 by 1"));

        String expected =
                ".#..#.#" + System.lineSeparator() +
                "#.#...." + System.lineSeparator() +
                ".#....." + System.lineSeparator();

        assertEquals(expected, display.toString());
    }

}