package pl.samouczekprogramisty.szs.aoc2016.day12.instructions;

import pl.samouczekprogramisty.szs.aoc2016.day12.DataHolder;


public class Jump implements Instruction {
    protected static final Integer SHOULDNT_JUMP = 0;

    protected final DataHolder dataHolder;
    protected final DataHolder offset;

    public Jump(DataHolder dataHolder, DataHolder offset) {
        this.dataHolder = dataHolder;
        this.offset = offset;
    }

    @Override
    public void execute(InstructionStack instructionStack) {
        if (SHOULDNT_JUMP.equals(dataHolder.getValue())) {
            instructionStack.moveToNextCommand();
        }
        else {
            instructionStack.jump(offset.getValue());
        }
    }

    @Override
    public String toString() {
        return "JMP " + dataHolder + " " + offset;
    }
}
