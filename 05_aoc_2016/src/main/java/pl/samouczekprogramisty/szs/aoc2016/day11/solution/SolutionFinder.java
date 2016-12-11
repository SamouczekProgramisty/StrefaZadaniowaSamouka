package pl.samouczekprogramisty.szs.aoc2016.day11.solution;


import pl.samouczekprogramisty.szs.aoc2016.day11.Building;
import pl.samouczekprogramisty.szs.aoc2016.day11.Floor;
import pl.samouczekprogramisty.szs.aoc2016.day11.Generator;
import pl.samouczekprogramisty.szs.aoc2016.day11.Microchip;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class SolutionFinder {


    public static final Generator HG = new Generator("H");
    public static final Generator LG = new Generator("L");
    public static final Microchip HM = new Microchip("H");
    public static final Microchip LM = new Microchip("L");

    private static final Map<Building, Integer> numberOfStepsRequired = new HashMap<>();


    public static void main(String ... x) {
        Building building = new Building(
                0,
                Arrays.asList(
                    new Floor(HM, LM),
                    new Floor(HG),
                    new Floor(LG),
                    new Floor())
        );

        building = x();
        building = y();
        Deque<Building> tracksToCheck = new ArrayDeque<>();
        tracksToCheck.push(building);

        Deque<Building> currentIteration = new ArrayDeque<>();
        currentIteration.push(building);
        Deque<Building> nextIteration = new ArrayDeque<>();

        numberOfStepsRequired.put(building, 0);

        int currentStep = 0;

        do {
            currentStep++;
            System.out.println("Iteration " + currentStep + " (" + numberOfStepsRequired.size() + ")");
            while(currentIteration.size() != 0) {
                building = currentIteration.pop();
                for (Building newBuilding : building.listPossibleMoves()) {
                    if (newBuilding.isSolved()) {
                        System.out.println("YAY SOLVED");
                        System.out.println(currentStep);
                        return;
                    }
                    if (!numberOfStepsRequired.containsKey(newBuilding)) {
                        numberOfStepsRequired.put(newBuilding, currentStep);
                        nextIteration.push(newBuilding);
                    }
                }
            }
            currentIteration = nextIteration;
            nextIteration = new ArrayDeque<>();
        }
        while (currentIteration.size() != 0);
    }

    private static Building x() {
        Generator thuliumG = new Generator("thulium");
        Microchip thuliumM = new Microchip("thulium");
        Generator plutoniumG = new Generator("plutonium");
        Microchip plutoniumM = new Microchip("plutonium");
        Generator strontiumG = new Generator("strontium");
        Microchip strontiumM = new Microchip("strontium");
        Generator promethiumG = new Generator("promethium");
        Microchip promethiumM = new Microchip("promethium");
        Generator rutheniumG = new Generator("ruthenium");
        Microchip rutheniumM = new Microchip("ruthenium");

        return new Building(
                0,
                Arrays.asList(
                        new Floor(thuliumG, thuliumM, plutoniumG, strontiumG),
                        new Floor(plutoniumM, strontiumM),
                        new Floor(promethiumG, promethiumM, rutheniumG, rutheniumM),
                        new Floor())
        );
    }

    private static Building y() {
        Generator thuliumG = new Generator("thulium");
        Microchip thuliumM = new Microchip("thulium");
        Generator plutoniumG = new Generator("plutonium");
        Microchip plutoniumM = new Microchip("plutonium");
        Generator strontiumG = new Generator("strontium");
        Microchip strontiumM = new Microchip("strontium");
        Generator promethiumG = new Generator("promethium");
        Microchip promethiumM = new Microchip("promethium");
        Generator rutheniumG = new Generator("ruthenium");
        Microchip rutheniumM = new Microchip("ruthenium");
        Generator eleriumG = new Generator("elerium");
        Microchip eleriumM = new Microchip("elerium");
        Generator dilithiumG = new Generator("dilithium");
        Microchip dilithiumM = new Microchip("dilithium");

        return new Building(
                0,
                Arrays.asList(
                        new Floor(thuliumG, thuliumM, plutoniumG, strontiumG, eleriumG, eleriumM, dilithiumG, dilithiumM),
                        new Floor(plutoniumM, strontiumM),
                        new Floor(promethiumG, promethiumM, rutheniumG, rutheniumM),
                        new Floor())
        );
    }
}
