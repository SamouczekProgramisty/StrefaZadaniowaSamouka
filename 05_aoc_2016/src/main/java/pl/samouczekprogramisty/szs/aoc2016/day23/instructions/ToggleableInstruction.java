package pl.samouczekprogramisty.szs.aoc2016.day23.instructions;


import pl.samouczekprogramisty.szs.aoc2016.day12.instructions.Instruction;

public interface ToggleableInstruction extends Instruction {
    void execute(InstructionStackToggle instructionStack);
    ToggleableInstruction toggle();
}
