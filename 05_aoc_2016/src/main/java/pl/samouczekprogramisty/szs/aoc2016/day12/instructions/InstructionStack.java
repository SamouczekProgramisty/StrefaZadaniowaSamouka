package pl.samouczekprogramisty.szs.aoc2016.day12.instructions;


import java.util.List;

public class InstructionStack {
    private Instruction[] instructions;

    private int instructionPointer = 0;

    public InstructionStack(List<Instruction> instructions) {
        this.instructions = instructions.toArray(new Instruction[instructions.size()]);
    }

    public void execute() {
        while (instructionPointer < instructions.length) {
            Instruction instruction = instructions[instructionPointer];
            instruction.execute(this);
        }
    }

    protected void jump(Integer by) {
        instructionPointer = instructionPointer + by;
    }

    protected void moveToNextCommand() {
        instructionPointer++;
    }
}
