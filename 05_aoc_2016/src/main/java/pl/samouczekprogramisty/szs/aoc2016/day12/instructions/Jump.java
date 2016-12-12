package pl.samouczekprogramisty.szs.aoc2016.day12.instructions;

import pl.samouczekprogramisty.szs.aoc2016.day12.DataHolder;


public class Jump implements Instruction {
    private static final Integer SHOULDNT_JUMP = 0;

    private final DataHolder dataHolder;
    private final Integer offset;

    public Jump(DataHolder dataHolder, Integer offset) {
        this.dataHolder = dataHolder;
        this.offset = offset;
    }

    @Override
    public void execute(InstructionStack instructionStack) {
        if (SHOULDNT_JUMP.equals(dataHolder.getValue())) {
            instructionStack.moveToNextCommand();
        }
        else {
            instructionStack.jump(offset);
        }
    }

    @Override
    public String toString() {
        return "JMP " + dataHolder + " " + offset;
    }
}
