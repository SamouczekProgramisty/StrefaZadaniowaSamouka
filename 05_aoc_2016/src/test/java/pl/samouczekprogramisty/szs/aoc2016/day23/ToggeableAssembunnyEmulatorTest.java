package pl.samouczekprogramisty.szs.aoc2016.day23;


import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.day12.Registers;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ToggeableAssembunnyEmulatorTest {
    @Test
    public void shouldPassExample() {
        ToggeableAssembunnyEmulator emulator = new ToggeableAssembunnyEmulator(Arrays.asList(
                "cpy 2 a",
                "tgl a",
                "tgl a",
                "tgl a",
                "cpy 1 a",
                "dec a",
                "dec a"
        ));

        emulator.run();
        Registers registers = emulator.getRegisters();
        assertEquals(Integer.valueOf(3), registers.getRegister("a").getValue());
    }
}
