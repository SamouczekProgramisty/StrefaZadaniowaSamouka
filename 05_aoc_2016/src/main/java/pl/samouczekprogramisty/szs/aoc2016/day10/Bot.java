package pl.samouczekprogramisty.szs.aoc2016.day10;


import java.util.Objects;

public class Bot implements DataSink {
    private final Integer id;
    private Integer lower;
    private Integer higher;

    public Bot(Integer id) {
        this.id = id;
    }

    @Override
    public void consumeData(Integer value) {
        if (isFilled()) {
            throw new IllegalStateException("Adding number to filled pair! " + lower + " " + higher + " " + id);
        }
        if (lower == null) {
            lower = value;
        }
        else if (lower < value) {
            higher = value;
        }
        else {
            higher = lower;
            lower = value;
        }
    }

    @Override
    public boolean isFilled() {
        return lower != null && higher != null;
    }

    public void emitData(DataSink lowDataSink, DataSink highDataSink) {
        lowDataSink.consumeData(lower);
        highDataSink.consumeData(higher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bot bot = (Bot) o;
        return Objects.equals(id, bot.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Bot(%s, (%s, %s))", id, lower, higher);
    }

    public Integer getId() {
        return id;
    }

    public boolean isResponsibleFor(Integer lowerValue, Integer higherValue) {
        boolean lowerEqual = areValuesEqual(lower, lowerValue);
        boolean higherEqual = areValuesEqual(higher, higherValue);
        return lowerEqual && higherEqual;
    }

    private static boolean areValuesEqual(Integer value1, Integer value2) {
        if (value1 != null) {
            return value1.equals(value2);
        }
        return value2 == null;
    }
}
