package pl.samouczekprogramisty.szs.aoc2016.day20;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IpRangeFinderTest {

    private IpRangeFinder rangeFinder;

    @Before
    public void setUp() {
        rangeFinder = new IpRangeFinder(IpRange.parse("5-8"), IpRange.parse("0-2"), IpRange.parse("4-7"));
    }

    @Test
    public void shouldShowFirstValidIp() {
        assertEquals(3, rangeFinder.getFirstAllowedIp());
    }

    @Test
    public void shouldFindNumberOfNotCoveredIps() {
        assertEquals(1, rangeFinder.getAllowedIpsCount());
    }
}