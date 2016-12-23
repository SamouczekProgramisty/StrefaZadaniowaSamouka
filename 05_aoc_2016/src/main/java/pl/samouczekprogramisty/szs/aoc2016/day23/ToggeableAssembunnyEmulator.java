package pl.samouczekprogramisty.szs.aoc2016.day23;

import pl.samouczekprogramisty.szs.aoc2016.day12.Registers;
import pl.samouczekprogramisty.szs.aoc2016.day23.instructions.InstructionStackToggle;
import pl.samouczekprogramisty.szs.aoc2016.day23.instructions.ToggeableInstructionFactory;
import pl.samouczekprogramisty.szs.aoc2016.day23.instructions.ToggleableInstruction;

import java.util.LinkedList;
import java.util.List;

public class ToggeableAssembunnyEmulator {
    private final Registers registers;
    private final InstructionStackToggle instructionStack;

    public ToggeableAssembunnyEmulator(List<String> instructions) {
        this(instructions, new Registers());
    }

    public ToggeableAssembunnyEmulator(List<String> instructions, Registers registers) {
        this.registers = registers;
        instructionStack = instantiateInstructions(instructions);
    }

    protected InstructionStackToggle instantiateInstructions(List<String> instructionsToParse) {
        List<ToggleableInstruction> instructions = new LinkedList<>();
        for (String instruction : instructionsToParse) {
            instructions.add(ToggeableInstructionFactory.parse(registers, instruction));
        }

        return new InstructionStackToggle(instructions);
    }

    public void run() {
        instructionStack.execute();
    }

    public Registers getRegisters() {
        return registers;
    }
}
