package pl.samouczekprogramisty.szs.aoc2016.day12.instructions;


import pl.samouczekprogramisty.szs.aoc2016.day12.DataHolder;

public class Copy implements Instruction {
    protected final DataHolder source;
    protected final DataHolder destination;

    public Copy(DataHolder source, DataHolder destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public void execute(InstructionStack instructionStack) {
        destination.setValue(source.getValue());
        instructionStack.moveToNextCommand();
    }

    @Override
    public String toString() {
        return "CPY " + source + " " + destination;
    }
}
