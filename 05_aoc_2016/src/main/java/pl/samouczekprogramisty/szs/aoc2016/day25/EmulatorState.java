package pl.samouczekprogramisty.szs.aoc2016.day25;

import pl.samouczekprogramisty.szs.aoc2016.day12.Register;
import pl.samouczekprogramisty.szs.aoc2016.day12.Registers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EmulatorState {
    private final Map<String, Integer> registerValue;
    private final Integer instructionPointer;

    public EmulatorState(Registers registers, Integer instructionPointer) {
        this.instructionPointer = instructionPointer;
        registerValue = new HashMap<>();
        for (Register register : registers) {
            registerValue.put(register.getName(), register.getValue());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmulatorState that = (EmulatorState) o;
        return Objects.equals(registerValue, that.registerValue) &&
                Objects.equals(instructionPointer, that.instructionPointer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registerValue, instructionPointer);
    }
}
