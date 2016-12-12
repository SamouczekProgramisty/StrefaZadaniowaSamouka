package pl.samouczekprogramisty.szs.aoc2016.day12.instructions;


import pl.samouczekprogramisty.szs.aoc2016.day12.DataHolder;
import pl.samouczekprogramisty.szs.aoc2016.day12.Register;

public class Copy implements Instruction {
    private final DataHolder source;
    private final Register detination;

    public Copy(DataHolder source, Register destination) {
        this.source = source;
        this.detination = destination;
    }

    @Override
    public void execute(InstructionStack instructionStack) {
        detination.setValue(source.getValue());
        instructionStack.moveToNextCommand();
    }

    @Override
    public String toString() {
        return "CPY " + source + " " + detination;
    }
}
