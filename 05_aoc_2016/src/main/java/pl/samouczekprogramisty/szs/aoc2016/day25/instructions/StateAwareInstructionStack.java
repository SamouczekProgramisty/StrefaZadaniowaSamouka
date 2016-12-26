package pl.samouczekprogramisty.szs.aoc2016.day25.instructions;

import pl.samouczekprogramisty.szs.aoc2016.day12.Registers;
import pl.samouczekprogramisty.szs.aoc2016.day23.instructions.InstructionStackToggle;
import pl.samouczekprogramisty.szs.aoc2016.day23.instructions.ToggleableInstruction;
import pl.samouczekprogramisty.szs.aoc2016.day25.EmulatorState;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StateAwareInstructionStack extends InstructionStackToggle {
    private final Set<EmulatorState> states = new HashSet<>();
    private final Registers registers;

    public StateAwareInstructionStack(Registers registers, List<ToggleableInstruction> instructions) {
        super(instructions);
        this.registers = registers;
    }

    public static class CycleDetectedException extends RuntimeException {
    }

    protected void cleanAfterCommandExecution() {
        EmulatorState currentState = new EmulatorState(registers, instructionPointer);
        if (states.contains(currentState)) {
            throw new CycleDetectedException();
        }
        states.add(currentState);
    }
}
