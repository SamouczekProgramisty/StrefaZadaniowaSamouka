package pl.samouczekprogramisty.szs.aoc2016.day12.instructions;

import pl.samouczekprogramisty.szs.aoc2016.day12.Register;

public class Decrement implements Instruction {

    protected final Register register;

    public Decrement(Register register) {
        this.register = register;
    }

    @Override
    public void execute(InstructionStack instructionStack) {
        register.decrement();
        instructionStack.moveToNextCommand();
    }

    @Override
    public String toString() {
        return "DEC " + register;
    }
}
