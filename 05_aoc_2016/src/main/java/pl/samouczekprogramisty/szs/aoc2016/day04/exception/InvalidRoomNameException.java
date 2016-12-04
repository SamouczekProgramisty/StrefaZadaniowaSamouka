package pl.samouczekprogramisty.szs.aoc2016.day04.exception;

public class InvalidRoomNameException extends RuntimeException {
    public InvalidRoomNameException(String invalidRoomName) {
        super(invalidRoomName);
    }
}
