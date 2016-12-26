package pl.samouczekprogramisty.szs.aoc2016.day25;

import org.junit.BeforeClass;
import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;
import pl.samouczekprogramisty.szs.aoc2016.day12.Registers;
import pl.samouczekprogramisty.szs.aoc2016.day25.instructions.OutToggle;
import pl.samouczekprogramisty.szs.aoc2016.day25.instructions.StateAwareInstructionStack;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CycleAwareEmulatorTest {
    private static List<String> instructions;

    @BeforeClass
    public static void setUpClass() {
        instructions = InputFileReader.readFileAsLines("day25_input.txt");
    }

    @Test
    public void shouldSolvePuzzle1() {
        int registerValue = 0;
        while (true) {
            Registers registers = new Registers();
            registers.getRegister("a").setValue(registerValue);
            CycleAwareEmulator emulator = new CycleAwareEmulator(instructions, registers);
            try {
                emulator.run();
            }
            catch (OutToggle.IllegalOutputSequenceException e) {
                registerValue++;
                continue;
            }
            catch (StateAwareInstructionStack.CycleDetectedException e) {
                break;
            }
        }
        assertEquals(192, registerValue);
    }
}