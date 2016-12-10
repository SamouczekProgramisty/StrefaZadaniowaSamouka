package pl.samouczekprogramisty.szs.aoc2016.day10;

public interface DataSink {
    enum Type {
        bot,
        output
    }

    boolean isFilled();
    void consumeData(Integer data);
}
