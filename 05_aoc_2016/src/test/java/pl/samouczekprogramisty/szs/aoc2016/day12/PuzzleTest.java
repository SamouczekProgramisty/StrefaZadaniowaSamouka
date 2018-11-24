package pl.samouczekprogramisty.szs.aoc2016.day12;

import org.junit.jupiter.api.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldSolvePuzzle() {
        List<String> instructions = InputFileReader.readFileAsLines("day12_input.txt");

        Registers registers = new Registers();
        AssembunnyEmulator emulator = new AssembunnyEmulator(instructions, registers);
        emulator.run();

        Register register = registers.getRegister("a");
        assertEquals(Integer.valueOf(318117), register.getValue());
    }

    @Test
    public void shouldSolvePuzzle2() {
        List<String> instructions = InputFileReader.readFileAsLines("day12_input.txt");

        Registers registers = new Registers();
        registers.getRegister("c").increment();

        AssembunnyEmulator emulator = new AssembunnyEmulator(instructions, registers);
        emulator.run();

        Register register = emulator.getRegisters().getRegister("a");
        assertEquals(Integer.valueOf(9227771), register.getValue());
    }
}
