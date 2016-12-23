package pl.samouczekprogramisty.szs.aoc2016.day12;

import pl.samouczekprogramisty.szs.aoc2016.day12.instructions.InstructionStack;
import pl.samouczekprogramisty.szs.aoc2016.day12.instructions.Instruction;
import pl.samouczekprogramisty.szs.aoc2016.day12.instructions.InstructionFactory;

import java.util.LinkedList;
import java.util.List;

public class AssembunnyEmulator {
    protected final InstructionStack instructionStack;
    protected final Registers registers;

    public AssembunnyEmulator(List<String> instructions) {
        this(instructions, new Registers());
    }

    public AssembunnyEmulator(List<String> instructions, Registers registers) {
        this.registers = registers;
        instructionStack = instantiateInstructions(instructions);
    }

    protected InstructionStack instantiateInstructions(List<String> instructionsToParse) {
        List<Instruction> instructions = new LinkedList<Instruction>();
        for (String instruction : instructionsToParse) {
            instructions.add(InstructionFactory.parse(registers, instruction));
        }

        return new InstructionStack<>(instructions);
    }

    public void run() {
        instructionStack.execute();
    }

    public Registers getRegisters() {
        return registers;
    }
}
