package pl.samouczekprogramisty.szs.aoc2016.day23.instructions;

import pl.samouczekprogramisty.szs.aoc2016.day12.Register;
import pl.samouczekprogramisty.szs.aoc2016.day12.instructions.InstructionStack;

public class Toggle implements ToggleableInstruction {
    protected final Register register;

    public Toggle(Register register) {
        this.register = register;
    }

    @Override
    public void execute(InstructionStackToggle instructionStack) {
        instructionStack.toggle(register.getValue());
        instructionStack.moveToNextCommand();
    }

    @Override
    public void execute(InstructionStack instructionStack) {
        InstructionStackToggle toggeableStack = (InstructionStackToggle) instructionStack;
        execute(toggeableStack);
    }

    @Override
    public String toString() {
        return "TGL " + register;
    }

    @Override
    public ToggleableInstruction toggle() {
        return new IncrementToggle(register);
    }

}
