package pl.samouczekprogramisty.szs.aoc2016.day25;

import pl.samouczekprogramisty.szs.aoc2016.day12.Registers;
import pl.samouczekprogramisty.szs.aoc2016.day23.instructions.InstructionStackToggle;
import pl.samouczekprogramisty.szs.aoc2016.day23.instructions.ToggleableInstruction;
import pl.samouczekprogramisty.szs.aoc2016.day25.instructions.InstructionFactory;
import pl.samouczekprogramisty.szs.aoc2016.day25.instructions.StateAwareInstructionStack;

import java.util.LinkedList;
import java.util.List;

public class CycleAwareEmulator {
    private final Registers registers;
    private final InstructionStackToggle instructionStack;

    public CycleAwareEmulator(List<String> instructions, Registers registers) {
        this.registers = registers;
        instructionStack = instantiateInstructions(instructions);
    }

    protected InstructionStackToggle instantiateInstructions(List<String> instructionsToParse) {
        List<ToggleableInstruction> instructions = new LinkedList<>();
        for (String instruction : instructionsToParse) {
            instructions.add(InstructionFactory.parse(registers, instruction));
        }

        return new StateAwareInstructionStack(registers, instructions);
    }

    public void run() {
        instructionStack.execute();
    }

    public Registers getRegisters() {
        return registers;
    }
}