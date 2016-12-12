package pl.samouczekprogramisty.szs.aoc2016.day11.solution;


import pl.samouczekprogramisty.szs.aoc2016.day11.Building;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class SolutionFinder {

    private SolutionFinder() {
    }

    public static Integer minimumNumberOfStepsRequired(Building building) {
        Map<Building, Integer> numberOfStepsRequired = new HashMap<>();
        numberOfStepsRequired.put(building, 0);

        Deque<Building> currentIteration = new ArrayDeque<>();
        currentIteration.push(building);
        int currentStep = 0;

        Deque<Building> nextIteration = new ArrayDeque<>();

        do {
            currentStep++;
            System.out.println("Iteration " + currentStep + " (" + numberOfStepsRequired.size() + ")");
            while(currentIteration.size() != 0) {
                building = currentIteration.pop();
                for (Building newBuilding : building.listPossibleMoves()) {
                    if (newBuilding.isSolved()) {
                        return currentStep;
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

        throw new IllegalStateException("Sould already be solved!");
    }
}
