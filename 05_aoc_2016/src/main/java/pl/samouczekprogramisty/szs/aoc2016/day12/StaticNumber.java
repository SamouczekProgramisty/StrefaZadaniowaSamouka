package pl.samouczekprogramisty.szs.aoc2016.day12;

public class StaticNumber implements DataHolder {
    private final Integer value;

    public StaticNumber(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + value + ']';
    }
}
