package pl.samouczekprogramisty.szs.aoc2016.day23.instructions;


import pl.samouczekprogramisty.szs.aoc2016.day12.DataHolder;
import pl.samouczekprogramisty.szs.aoc2016.day12.instructions.Jump;

public class JumpToggle extends Jump implements ToggleableInstruction {

    public JumpToggle(DataHolder dataHolder, DataHolder offset) {
        super(dataHolder, offset);
    }

    @Override
    public void execute(InstructionStackToggle instructionStack) {
        super.execute(instructionStack);
    }

    @Override
    public ToggleableInstruction toggle() {
        return new CopyToggle(dataHolder, offset);
    }
}
