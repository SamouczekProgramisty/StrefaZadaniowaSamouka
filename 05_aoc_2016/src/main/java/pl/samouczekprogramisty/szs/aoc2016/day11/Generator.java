package pl.samouczekprogramisty.szs.aoc2016.day11;


import java.util.Objects;

public class Generator implements ElectronicDevice {
    private final String type;

    public Generator(String type) {
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
        Generator generator = (Generator) o;
        return Objects.equals(type, generator.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public boolean isGenerator() {
        return true;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "G(" + type + ")";
    }
}
