package pl.samouczekprogramisty.szs.aoc2016.day10.command;


public interface Command {
    void execute();
    boolean canBeExecuted();
}
