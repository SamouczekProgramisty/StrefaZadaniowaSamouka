package pl.samouczekprogramisty.szs.aoc2016.day25.instructions;

import pl.samouczekprogramisty.szs.aoc2016.day12.DataHolder;
import pl.samouczekprogramisty.szs.aoc2016.day12.Register;
import pl.samouczekprogramisty.szs.aoc2016.day12.instructions.InstructionStack;
import pl.samouczekprogramisty.szs.aoc2016.day23.instructions.IncrementToggle;
import pl.samouczekprogramisty.szs.aoc2016.day23.instructions.InstructionStackToggle;
import pl.samouczekprogramisty.szs.aoc2016.day23.instructions.ToggleableInstruction;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OutToggle implements ToggleableInstruction {
    private static final Set<Integer> ALLOWED_STATES = new HashSet<>(Arrays.asList(0, 1));

    private final DataHolder value;
    private Integer firstValue;

    public OutToggle(DataHolder value) {
        this.value = value;
    }

    @Override
    public void execute(InstructionStackToggle instructionStack) {
        detectIllegalSequence(value.getValue());
        instructionStack.moveToNextCommand();
    }

    @Override
    public void execute(InstructionStack instructionStack) {
        detectIllegalSequence(value.getValue());
        instructionStack.moveToNextCommand();
    }

    private void detectIllegalSequence(Integer nextValue) {
        if (!ALLOWED_STATES.contains(nextValue)) {
            throw new IllegalOutputSequenceException(nextValue.toString());
        }

        if (firstValue != null && firstValue.equals(nextValue)) {
            throw new IllegalOutputSequenceException(firstValue + " " + nextValue);
        }
        firstValue = nextValue;
    }

    @Override
    public ToggleableInstruction toggle() {
        return new IncrementToggle((Register) value);
    }

    public static class IllegalOutputSequenceException extends RuntimeException {
        public IllegalOutputSequenceException(String sequence) {
            super(sequence);
        }
    }
}
