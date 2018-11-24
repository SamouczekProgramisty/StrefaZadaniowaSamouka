package pl.samouczekprogramisty.szs.aoc2016.day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SapperTest {
    @Test
    public void shouldProperlyDecectTrapsInSampleRoom() {
        Sapper sapper = new Sapper("..^^.");
        assertEquals(".^^^^", sapper.getLine(2));
        assertEquals("^^..^", sapper.getLine(3));
    }

    @Test
    public void shoudProperlyDetectTrapsInMoreComplicatedRoom() {
        Sapper sapper = new Sapper(".^^.^.^^^^");
        sapper.getLine(10);

        String expected = ".^^.^.^^^^" + System.lineSeparator() +
                          "^^^...^..^" + System.lineSeparator() +
                          "^.^^.^.^^." + System.lineSeparator() +
                          "..^^...^^^" + System.lineSeparator() +
                          ".^^^^.^^.^" + System.lineSeparator() +
                          "^^..^.^^.." + System.lineSeparator() +
                          "^^^^..^^^." + System.lineSeparator() +
                          "^..^^^^.^^" + System.lineSeparator() +
                          ".^^^..^.^^" + System.lineSeparator() +
                          "^^.^^^..^^" + System.lineSeparator();

        assertEquals(38, sapper.getSafeTilesCount());
        assertEquals(expected, sapper.toString());
    }
}