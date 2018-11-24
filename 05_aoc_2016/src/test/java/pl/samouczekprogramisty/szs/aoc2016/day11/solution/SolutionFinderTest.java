package pl.samouczekprogramisty.szs.aoc2016.day11.solution;

import org.junit.Assert;
import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.day11.Building;
import pl.samouczekprogramisty.szs.aoc2016.day11.Floor;
import pl.samouczekprogramisty.szs.aoc2016.day11.Generator;
import pl.samouczekprogramisty.szs.aoc2016.day11.Microchip;

import java.util.Arrays;


public class SolutionFinderTest {
    @Test
    public void shouldFindMinimumNumberOfStepsForSampleProblem() {
        Generator HG = new Generator("H");
        Generator LG = new Generator("L");
        Microchip HM = new Microchip("H");
        Microchip LM = new Microchip("L");

        Building building = new Building(
                0,
                Arrays.asList(
                        new Floor(HM, LM),
                        new Floor(HG),
                        new Floor(LG),
                        new Floor()
                )
        );

        Assert.assertEquals(Integer.valueOf(11), SolutionFinder.minimumNumberOfStepsRequired(building));
    }
}