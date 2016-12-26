package pl.samouczekprogramisty.szs.aoc2016.day12.instructions;


import java.util.ArrayList;
import java.util.List;

public class InstructionStack<T extends Instruction> {
    protected ArrayList<T> instructions;
    protected int instructionPointer = 0;

    public InstructionStack(List<T> instructions) {
        this.instructions = new ArrayList<>(instructions);
    }

    public void execute() {
        while (instructionPointer < instructions.size()) {
            T instruction = instructions.get(instructionPointer);
            instruction.execute(this);
            cleanAfterCommandExecution();
        }
    }

    public void jump(Integer by) {
        instructionPointer = instructionPointer + by;
    }

    public void moveToNextCommand() {
        instructionPointer++;
    }

    protected void cleanAfterCommandExecution() {
        // just to extend in different implementations
    }
}
