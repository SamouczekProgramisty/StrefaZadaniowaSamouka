package pl.samouczekprogramisty.szs.aoc2016.day23.instructions;

import pl.samouczekprogramisty.szs.aoc2016.day12.Register;
import pl.samouczekprogramisty.szs.aoc2016.day12.instructions.Increment;

public class IncrementToggle extends Increment implements ToggleableInstruction {
    public IncrementToggle(Register register) {
        super(register);
    }

    @Override
    public void execute(InstructionStackToggle instructionStack) {
        super.execute(instructionStack);
    }

    @Override
    public ToggleableInstruction toggle() {
        return new DecrementToggle(register);
    }
}
