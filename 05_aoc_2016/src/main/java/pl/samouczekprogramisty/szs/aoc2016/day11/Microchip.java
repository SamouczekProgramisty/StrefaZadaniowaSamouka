package pl.samouczekprogramisty.szs.aoc2016.day11;


import java.util.Objects;

public class Microchip implements ElectronicDevice {
    private final String type;

    public Microchip(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Microchip microchip = (Microchip) o;
        return Objects.equals(type, microchip.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isGenerator() {
        return false;
    }

    @Override
    public String toString() {
        return "M(" + type + ")";
    }
}
