package pl.samouczekprogramisty.szs.aoc2016.day23.instructions;

import pl.samouczekprogramisty.szs.aoc2016.day12.Register;
import pl.samouczekprogramisty.szs.aoc2016.day12.instructions.Decrement;

public class DecrementToggle extends Decrement implements ToggleableInstruction {

    public DecrementToggle(Register register) {
        super(register);
    }

    @Override
    public void execute(InstructionStackToggle instructionStack) {
        super.execute(instructionStack);
    }

    @Override
    public ToggleableInstruction toggle() {
        return new IncrementToggle(register);
    }
}
