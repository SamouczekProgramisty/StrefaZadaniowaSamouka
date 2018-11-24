package pl.samouczekprogramisty.szs.aoc2016.day04;

import org.junit.jupiter.api.Test;
import pl.samouczekprogramisty.szs.aoc2016.day04.exception.InvalidRoomNameException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomNameDecoderTest {
    @Test
    public void shouldBeAbleToParseEncodedRoom1() {
        RoomNameDecoder decoder = new RoomNameDecoder("aaaaa-bbb-z-y-x-123[abxyz]");
        assertEquals(123, decoder.getSectorId());
        assertEquals("aaaaa-bbb-z-y-x", decoder.getRoomName());
    }

    @Test
    public void shouldBeAbleToParseEncodedRoom2() {
        RoomNameDecoder decoder = new RoomNameDecoder("a-b-c-d-e-f-g-h-987[abcde]");
        assertEquals(987, decoder.getSectorId());
        assertEquals("a-b-c-d-e-f-g-h", decoder.getRoomName());
    }

    @Test
    public void shouldBeAbleToParseEncodedRoom3() {
        RoomNameDecoder decoder = new RoomNameDecoder("not-a-real-room-404[oarel]");
        assertEquals(404, decoder.getSectorId());
        assertEquals("not-a-real-room", decoder.getRoomName());
    }

    @Test
    public void shouldBeAbleToParseEncodedRoom4() {
        assertThrows(InvalidRoomNameException.class, () -> new RoomNameDecoder("totally-real-room-200[decoy]"));
    }

    @Test
    public void shouldBeAbleToParseEncodedRoom5() {
        RoomNameDecoder decoder = new RoomNameDecoder("a-b-c-z-1[abcz]");
        assertEquals("b-c-d-a", decoder.getDecodedRoomName());
    }
}