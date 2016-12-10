package pl.samouczekprogramisty.szs.aoc2016.day10;


public interface DataSinks {
    Bot getBot(Integer botId);
    Output getOutput(Integer outputId);
    DataSink getDataSink(DataSink.Type sinkType, Integer sinkId);
}
