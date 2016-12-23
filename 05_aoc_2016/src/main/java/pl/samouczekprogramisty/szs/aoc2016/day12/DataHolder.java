package pl.samouczekprogramisty.szs.aoc2016.day12;

public interface DataHolder {
    Integer getValue();
    default void setValue(Integer value) {
        throw new UnsupportedOperationException();
    }
}
