package pl.samouczekprogramisty.szs.aoc2016.day20;

import org.junit.BeforeClass;
import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PuzzleTest {
    private static IpRangeFinder rangeFinder;

    @BeforeClass
    public static void setUpClass() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println("2365712272");
        List<IpRange> ranges = new LinkedList<>();
        for (String line : InputFileReader.readFileAsLines("day20_input.txt")) {
            ranges.add(IpRange.parse(line));
        }
        rangeFinder = new IpRangeFinder(ranges.toArray(new IpRange[ranges.size()]));
    }

    @Test
    public void shouldFindFirstAllowedIp() {
        assertEquals(4793564, rangeFinder.getFirstAllowedIp());
    }

    @Test
    public void shouldFindNumberOfallowedIps() {
        assertEquals(146, rangeFinder.getAllowedIpsCount());
    }
}
