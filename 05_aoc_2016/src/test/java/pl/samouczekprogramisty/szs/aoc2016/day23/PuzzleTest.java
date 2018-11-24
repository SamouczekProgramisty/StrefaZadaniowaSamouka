package pl.samouczekprogramisty.szs.aoc2016.day23;


import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;
import pl.samouczekprogramisty.szs.aoc2016.day12.Register;
import pl.samouczekprogramisty.szs.aoc2016.day12.Registers;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PuzzleTest {
    private static List<String> instructions;

    @BeforeClass
    public static void setUpClass() {
        instructions = InputFileReader.readFileAsLines("day23_input.txt");
    }

    @Test
    public void shouldSolvePuzzle1() {
        Registers registers = new Registers();
        registers.getRegister("a").setValue(7);

        ToggeableAssembunnyEmulator emulator = new ToggeableAssembunnyEmulator(instructions, registers);
        emulator.run();

        Register register = registers.getRegister("a");
        assertEquals(Integer.valueOf(14160), register.getValue());
    }

    @Test
    @Ignore("takes too long to finish")
    public void shouldSolvePuzzle2() {
        Registers registers = new Registers();
        registers.getRegister("a").setValue(12);

        ToggeableAssembunnyEmulator emulator = new ToggeableAssembunnyEmulator(instructions, registers);
        emulator.run();

        Register register = registers.getRegister("a");
        assertEquals(Integer.valueOf(479010720), register.getValue());
    }
}