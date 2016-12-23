package pl.samouczekprogramisty.szs.aoc2016.day23.instructions;

import pl.samouczekprogramisty.szs.aoc2016.day12.DataHolder;
import pl.samouczekprogramisty.szs.aoc2016.day12.instructions.Copy;

public class CopyToggle extends Copy implements ToggleableInstruction {
    public CopyToggle(DataHolder source, DataHolder destination) {
        super(source, destination);
    }

    @Override
    public void execute(InstructionStackToggle instructionStack) {
        super.execute(instructionStack);
    }

    @Override
    public ToggleableInstruction toggle() {
        return new JumpToggle(source, destination);
    }
}
