package pl.samouczekprogramisty.szs.aoc2016.day11;


import pl.samouczekprogramisty.szs.aoc2016.day11.solution.Move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Building {
    private final List<Floor> floors;
    private final Set<String> floorUnmachedDevies;
    private final Integer elevatorAtFloor;

    public Building(Integer whereIsElevator, List<Floor> floors) {
        this.floors = Collections.unmodifiableList(new ArrayList<>(floors));
        floorUnmachedDevies = initializeFloorRepresentation();
        elevatorAtFloor = whereIsElevator;
    }

    private Set<String> initializeFloorRepresentation() {
        Set<String> unmachedDevicesOnFloors = new HashSet<>();
        for (Floor floor : floors) {
            unmachedDevicesOnFloors.add(floor.toStringUnmachedDevices());
        }
        return unmachedDevicesOnFloors;
    }

    public boolean isSolved() {
        for (int floorIndex = 0; floorIndex < floors.size() - 1; floorIndex++) {
            if (!floors.get(floorIndex).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public List<Building> listPossibleMoves() {
        List<Building> newConfigurations = new ArrayList<>();
        Floor movingFromFloor = floors.get(elevatorAtFloor);
        List<Move> moves = movingFromFloor.listPossibleMoves();

        for (Move move : moves) {
            Floor newMovingFromFloor = movingFromFloor.removeDevices(move);
            for (Integer movingToIndex : whereElevatorCouldGo()) {
                Floor newMovingToFloor;
                List<Floor> newFloors = new LinkedList<>();
                try {
                    newMovingToFloor = floors.get(movingToIndex).addDevices(move);
                }
                catch (IllegalArgumentException e) {
                    // move was illegal - ignore the move
                    continue;
                }

                if (movingToIndex > elevatorAtFloor) {
                    newFloors.addAll(floors.subList(0, elevatorAtFloor));
                    newFloors.add(newMovingFromFloor);
                    newFloors.add(newMovingToFloor);
                    if (movingToIndex + 1 <= floors.size()) {
                        newFloors.addAll(floors.subList(movingToIndex + 1, floors.size()));
                    }
                }
                else {
                    newFloors.addAll(floors.subList(0, movingToIndex));
                    newFloors.add(newMovingToFloor);
                    newFloors.add(newMovingFromFloor);
                    if (elevatorAtFloor + 1 <= floors.size()) {
                        newFloors.addAll(floors.subList(elevatorAtFloor + 1, floors.size()));
                    }
                }
                newConfigurations.add(new Building(movingToIndex, newFloors));
            }
        }

        return newConfigurations;
    }

    private List<Integer> whereElevatorCouldGo() {
        List<Integer> whereElevatorCouldGo = new ArrayList<>();
        if (elevatorAtFloor > 0) {
            whereElevatorCouldGo.add(elevatorAtFloor - 1);
        }
        if (elevatorAtFloor < floors.size() - 1) {
            whereElevatorCouldGo.add(elevatorAtFloor + 1);
        }
        return whereElevatorCouldGo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Building building = (Building) o;

        return Objects.equals(floorUnmachedDevies, building.floorUnmachedDevies) &&
                Objects.equals(elevatorAtFloor, building.elevatorAtFloor);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(floors) + Objects.hashCode(elevatorAtFloor);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int floorIndex = floors.size() - 1; floorIndex >= 0; floorIndex--) {
            if (floorIndex == elevatorAtFloor) {
                sb.append(" * ");
            }
            else {
                sb.append("   ");
            }
            sb.append(floors.get(floorIndex));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
