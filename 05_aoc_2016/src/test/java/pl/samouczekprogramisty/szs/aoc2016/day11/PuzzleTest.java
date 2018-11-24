package pl.samouczekprogramisty.szs.aoc2016.day11;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.samouczekprogramisty.szs.aoc2016.day11.solution.SolutionFinder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuzzleTest {
    private static final Generator THULIUM_G = new Generator("thulium");
    private static final Microchip THULIUM_M = new Microchip("thulium");
    private static final Generator PLUTONIUM_G = new Generator("plutonium");
    private static final Microchip PLUTONIUM_M = new Microchip("plutonium");
    private static final Generator STRONTIUM_G = new Generator("strontium");
    private static final Microchip STRONTIUM_M = new Microchip("strontium");
    private static final Generator PROMETHIUM_G = new Generator("promethium");
    private static final Microchip PROMETHIUM_M = new Microchip("promethium");
    private static final Generator RUTHENIUM_G = new Generator("ruthenium");
    private static final Microchip RUTHENIUM_M = new Microchip("ruthenium");
    private static final Generator ELERIUM_G = new Generator("elerium");
    private static final Microchip ELERIUM_M = new Microchip("elerium");
    private static final Generator DILITHIUM_G = new Generator("dilithium");
    private static final Microchip DILITHIUM_M = new Microchip("dilithium");

    @Test
    public void shouldSolvePuzzleStep1() {
        Building building = new Building(
            0,
            Arrays.asList(
                new Floor(THULIUM_G, THULIUM_M, PLUTONIUM_G, STRONTIUM_G),
                new Floor(PLUTONIUM_M, STRONTIUM_M),
                new Floor(PROMETHIUM_G, PROMETHIUM_M, RUTHENIUM_G, RUTHENIUM_M),
                new Floor()
            )
        );

        assertEquals(Integer.valueOf(31), SolutionFinder.minimumNumberOfStepsRequired(building));
    }

    @Test
    @Disabled
    public void shouldSolvePuzzleStep2() {
        Building building = new Building(
            0,
            Arrays.asList(
                new Floor(THULIUM_G, THULIUM_M, PLUTONIUM_G, STRONTIUM_G, ELERIUM_G, ELERIUM_M, DILITHIUM_G, DILITHIUM_M),
                new Floor(PLUTONIUM_M, STRONTIUM_M),
                new Floor(PROMETHIUM_G, PROMETHIUM_M, RUTHENIUM_G, RUTHENIUM_M),
                new Floor()
            )
        );

        assertEquals(Integer.valueOf(31), SolutionFinder.minimumNumberOfStepsRequired(building));
    }
}
