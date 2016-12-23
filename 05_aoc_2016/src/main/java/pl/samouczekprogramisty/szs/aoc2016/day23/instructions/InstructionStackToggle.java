package pl.samouczekprogramisty.szs.aoc2016.day23.instructions;

import pl.samouczekprogramisty.szs.aoc2016.day12.instructions.InstructionStack;

import java.util.List;

public class InstructionStackToggle extends InstructionStack<ToggleableInstruction> {
    public InstructionStackToggle(List<ToggleableInstruction> instructions) {
        super(instructions);
    }

    protected void toggle(Integer offset) {
        int toggledIndex = instructionPointer + offset;
        if (toggledIndex >= instructions.size() || toggledIndex < 0) {
            return;
        }
        ToggleableInstruction newInstruction = instructions.get(toggledIndex).toggle();
        instructions.set(toggledIndex, newInstruction);
    }

}
