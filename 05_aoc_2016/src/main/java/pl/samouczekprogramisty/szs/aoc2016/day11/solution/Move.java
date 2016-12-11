package pl.samouczekprogramisty.szs.aoc2016.day11.solution;


import pl.samouczekprogramisty.szs.aoc2016.day11.ElectronicDevice;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Move {
    private final ElectronicDevice device1;
    private final ElectronicDevice device2;

    public Move(ElectronicDevice device1) {
        this(device1, null);
    }

    public Move(ElectronicDevice device1, ElectronicDevice device2) {
        this.device1 = device1;
        this.device2 = device2;
    }

    @Override
    public String toString() {
        return "Move(" + device1 + ", " + device2 + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Move move = (Move) o;
        return Objects.equals(device1, move.device1) &&
                Objects.equals(device2, move.device2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(device1, device2);
    }

    public ElectronicDevice getDevice1() {
        return device1;
    }

    public ElectronicDevice getDevice2() {
        return device2;
    }

    public List<ElectronicDevice> devices() {
        if (device2 != null) {
            return Arrays.asList(device1, device2);
        }
        return Arrays.asList(device1);
    }
}
