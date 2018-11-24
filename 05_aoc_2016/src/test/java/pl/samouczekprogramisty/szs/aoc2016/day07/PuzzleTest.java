package pl.samouczekprogramisty.szs.aoc2016.day07;

import org.junit.jupiter.api.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldFindAllValidIPv7Addresses() {
        int supportsTLS = 0;
        int supportsSSL = 0;

        for (String address : InputFileReader.readFileAsLines("day07_input.txt")) {
            IPv7 iPv7 = new IPv7(address);
            if (iPv7.supportsTLS()) {
                supportsTLS++;
            }
            if (iPv7.supportsSSL()) {
                supportsSSL++;
            }
        }

        assertEquals(115, supportsTLS);
        assertEquals(231, supportsSSL);
    }
}
