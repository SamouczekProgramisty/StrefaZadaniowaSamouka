package pl.samouczekprogramisty.szs.aoc2016.day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DragonChecksumTest {
    @Test
    public void shouldCreateProperSequence() {
        assertEquals("1111000010100101011110000", new DragonChecksum("111100001010", 20).getSequence());
    }

    @Test
    public void shouldProduceValidChecksum() {
        assertEquals("100", new DragonChecksum("110010110100", 12).getChecksum());
    }

    @Test
    public void shouldPassTheGivenExample() {
        assertEquals("01100", new DragonChecksum("10000", 20).getChecksum());
    }
}