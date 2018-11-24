package pl.samouczekprogramisty.szs.aoc2016.day04;

import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;
import pl.samouczekprogramisty.szs.aoc2016.day04.exception.InvalidRoomNameException;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldSolvePuzzle() {
        long sectorSum = 0L;
        List<RoomNameDecoder> potentialLuckyRooms = new LinkedList<>();

        for (String encodedRoom : InputFileReader.readFileAsLines("day04_input.txt")) {
            try {
                RoomNameDecoder decoder = new RoomNameDecoder(encodedRoom);
                sectorSum += decoder.getSectorId();
                if (decoder.getDecodedRoomName().contains("northpole")) {
                    potentialLuckyRooms.add(decoder);
                }
            }
            catch (InvalidRoomNameException e) {
                // not important
            }
        }
        assertEquals(245102, sectorSum);
    }
}
