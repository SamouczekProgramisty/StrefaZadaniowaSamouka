package pl.samouczekprogramisty.szs.aoc2016.day12;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class AssembunnyEmulatorTest {
    @Test
    public void shouldPass() {
        AssembunnyEmulator emulator = new AssembunnyEmulator(Arrays.asList(
                "cpy 41 a",
                "inc a",
                "inc a",
                "dec a",
                "jnz a 2",
                "dec a"
        ));

        emulator.run();
        Registers registers = emulator.getRegisters();
        assertEquals(Integer.valueOf(42), registers.getRegister("a").getValue());
    }

}