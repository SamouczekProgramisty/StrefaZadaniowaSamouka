package pl.samouczekprogramisty.szs.aoc2016.day12.instructions;

import pl.samouczekprogramisty.szs.aoc2016.day12.Register;

public class Increment implements Instruction {

    protected final Register register;

    public Increment(Register register) {
        this.register = register;
    }

    @Override
    public void execute(InstructionStack instructionStack) {
        register.increment();
        instructionStack.moveToNextCommand();
    }

    @Override
    public String toString() {
        return "INC " + register;
    }
}
