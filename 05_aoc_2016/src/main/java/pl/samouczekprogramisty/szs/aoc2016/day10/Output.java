package pl.samouczekprogramisty.szs.aoc2016.day10;

public class Output implements DataSink {
    private Integer id;
    private Integer value;

    public Output(Integer id) {
        this.id = id;
    }

    @Override
    public void consumeData(Integer data) {
        if (isFilled()) {
            throw new IllegalStateException("Data was alredy pushed to this value!");
        }
        value = data;
    }

    @Override
    public boolean isFilled() {
        return value != null;
    }

    @Override
    public String toString() {
        return String.format("Output(%s, %s)", id, value);
    }

    public Integer getValue() {
        return value;
    }
}
