package pl.samouczekprogramisty.szs.aoc2016.day04;

import org.junit.Ignore;
import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;
import pl.samouczekprogramisty.szs.aoc2016.day04.exception.InvalidRoomNameException;

public class PuzzleTest {
    @Test
    @Ignore
    public void shouldSolvePuzzle() {
        long sectorSum = 0L;
        for (String encodedRoom : InputFileReader.readFileAsLines("day04_input.txt")) {
            try {
                RoomNameDecoder decoder = new RoomNameDecoder(encodedRoom);
                sectorSum += decoder.getSectorId();
                System.out.println(decoder.getDecodedRoomName() + " " + decoder.getSectorId());
            }
            catch (InvalidRoomNameException e) {
                // not important
            }
        }
        System.out.println(sectorSum);
    }
}
