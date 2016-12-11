package pl.samouczekprogramisty.szs.aoc2016.day11;


import pl.samouczekprogramisty.szs.aoc2016.day11.solution.Move;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Floor {
    private final Map<String, Generator> generators;
    private final Map<String, Microchip> microchips;

    public Floor(Set<Generator> generators, Set<Microchip> microchips) {
        Map<String, Microchip> tempMicrochips = new HashMap<>();
        Map<String, Generator> tempGenerators = new HashMap<>();

        for (Generator generator : generators) {
            tempGenerators.put(generator.getType(), generator);
        }
        for (Microchip microchip : microchips) {
            tempMicrochips.put(microchip.getType(), microchip);
        }

        this.generators = Collections.unmodifiableMap(tempGenerators);
        this.microchips = Collections.unmodifiableMap(tempMicrochips);

        validateState();
    }

    public Floor(ElectronicDevice ... devices) {
        Map<String, Microchip> tempMicrochips = new HashMap<>();
        Map<String, Generator> tempGenerators = new HashMap<>();

        for (ElectronicDevice device : devices) {
            if (device.isGenerator()) {
                Generator generator = (Generator) device;
                tempGenerators.put(generator.getType(), generator);
            }
            else {
                Microchip microchip = (Microchip) device;
                tempMicrochips.put(microchip.getType(), microchip);
            }
        }

        generators = Collections.unmodifiableMap(tempGenerators);
        microchips = Collections.unmodifiableMap(tempMicrochips);

        validateState();
    }

    private void validateState() {
        Set<String> microchipTypes = new HashSet<>(microchips.keySet());
        Set<String> generatorTypes = new HashSet<>(generators.keySet());

        microchipTypes.removeAll(generators.keySet());

        if (!generatorTypes.isEmpty() && !microchipTypes.isEmpty()) {
            throw new IllegalArgumentException("Microchips " + microchipTypes + " doesn't have generators within " + generatorTypes + "!");
        }
    }

    public boolean isEmpty() {
        return generators.isEmpty() && microchips.isEmpty();
    }

    public List<Move> listPossibleMoves() {
        List<Move> returnedValue = new LinkedList<>();

        returnedValue.addAll(listAllSingleGeneratorMoves());
        returnedValue.addAll(listAllSingleMicrochipMoves());
        returnedValue.addAll(listAllMicrochipWithGeneratorMoves());
        returnedValue.addAll(listAllDoubleMicrochipMoves());
        returnedValue.addAll(listAllDoubleGeneratorpMoves());

        return returnedValue;
    }

    private List<Move> listAllDoubleMicrochipMoves() {
        List<Move> moves = new LinkedList<>();
        Microchip[] values = microchips.values().toArray(new Microchip[microchips.values().size()]);
        for (int firstIndex = 0; firstIndex < values.length; firstIndex++) {
            for (int secondIndex = firstIndex + 1; secondIndex < values.length; secondIndex++) {
                moves.add(new Move(values[firstIndex], values[secondIndex]));
            }
        }
        return moves;
    }

    private List<Move> listAllDoubleGeneratorpMoves() {
        List<Move> moves = new LinkedList<>();
        Generator[] values = generators.values().toArray(new Generator[generators.values().size()]);
        for (int firstIndex = 0; firstIndex < values.length; firstIndex++) {
            for (int secondIndex = firstIndex + 1; secondIndex < values.length; secondIndex++) {
                Generator generator1 = values[firstIndex];
                Generator generator2 = values[secondIndex];
                if (canRemoveTwoGenerators(generator1, generator2)) {
                    moves.add(new Move(generator1, generator2));
                }
            }
        }
        return moves;
    }

    private List<Move> listAllSingleMicrochipMoves() {
        List<Move> moves = new LinkedList<>();
        for (Microchip microchip : microchips.values()) {
            moves.add(new Move(microchip));
        }
        return moves;
    }

    private List<Move> listAllMicrochipWithGeneratorMoves() {
        List<Move> moves = new LinkedList<>();
        for (Microchip microchip : microchips.values()) {
            String microchipType = microchip.getType();
            if (generators.containsKey(microchipType)) {
                moves.add(new Move(microchip, generators.get(microchipType)));
            }
        }
        return moves;
    }

    private List<Move> listAllSingleGeneratorMoves() {
        List<Move> moves = new LinkedList<>();
        for (Generator generator : generators.values()) {
            if (canMoveSingleGenerator(generator)) {
                moves.add(new Move(generator));
            }
        }
        return moves;
    }

    private boolean canMoveSingleGenerator(Generator generator) {
        String type = generator.getType();
        if (microchips.containsKey(type)) {
            return generators.size() == 1;
        }
        return true;
    }

    private boolean canRemoveTwoGenerators(Generator generator1, Generator generator2) {
        Set<String> remainingGeneratorTypes = new HashSet<>(generators.keySet());
        remainingGeneratorTypes.remove(generator1.getType());
        remainingGeneratorTypes.remove(generator2.getType());

        if (remainingGeneratorTypes.size() > 0) {
            Set<String> remainingMicrochipTypes = new HashSet<>(microchips.keySet());
            remainingMicrochipTypes.removeAll(remainingGeneratorTypes);
            return remainingMicrochipTypes.isEmpty();
        }
        return true;
    }

    public Floor removeDevices(Move move) {
        Set<Generator> newGenerators = new HashSet<>(generators.values());
        Set<Microchip> newMicrochips = new HashSet<>(microchips.values());

        for (ElectronicDevice device : move.devices()) {
            if (device.isGenerator()) {
                if (!newGenerators.remove(device)) {
                    throw new RuntimeException("Something should have been removed!");
                }
            }
            else {
                if (!newMicrochips.remove(device)) {
                    throw new RuntimeException("Something should have been removed!");
                }
            }
        }

        return new Floor(newGenerators, newMicrochips);
    }

    public Floor addDevices(Move move) {
        Set<Generator> newGenerators = new HashSet<>(generators.values());
        Set<Microchip> newMicrochips = new HashSet<>(microchips.values());

        for (ElectronicDevice device : move.devices()) {
            if (device.isGenerator()) {
                if (!newGenerators.add((Generator) device)) {
                    throw new RuntimeException("Something should have been added!");
                }
            }
            else {
                if (!newMicrochips.add((Microchip) device)) {
                    throw new RuntimeException("Something should have been added!");
                }
            }
        }

        return new Floor(newGenerators, newMicrochips);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Generator generator : generators.values()) {
            sb.append(generator);
            sb.append(" ");
        }
        for (Microchip microchip : microchips.values()) {
            sb.append(microchip);
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Floor floor = (Floor) o;
        return Objects.equals(generators, floor.generators) &&
                Objects.equals(microchips, floor.microchips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generators, microchips);
    }
}