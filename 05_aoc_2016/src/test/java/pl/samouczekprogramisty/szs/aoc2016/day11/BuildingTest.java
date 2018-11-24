package pl.samouczekprogramisty.szs.aoc2016.day11;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BuildingTest {

    public static final Generator HG = new Generator("H");
    public static final Generator LG = new Generator("L");
    public static final Microchip HM = new Microchip("H");
    public static final Microchip LM = new Microchip("L");

    @Test
    public void shouldListPossibleMovements() {
        Building building = new Building(
                0,
                Arrays.asList(
                    new Floor(HM, LM),
                    new Floor(HG),
                    new Floor(LG),
                    new Floor()
                )
        );

        List<Building> expectedResult = Arrays.asList(
            new Building(
                1,
                Arrays.asList(
                    new Floor(LM),
                    new Floor(HM, HG),
                    new Floor(LG),
                    new Floor()
                )
            )
        );

        assertEquals(expectedResult, building.listPossibleMoves());
    }
}