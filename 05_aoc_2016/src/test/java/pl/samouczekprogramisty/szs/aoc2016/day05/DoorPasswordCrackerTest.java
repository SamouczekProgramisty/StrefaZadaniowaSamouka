package pl.samouczekprogramisty.szs.aoc2016.day05;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoorPasswordCrackerTest {
    @Test
    @Ignore
    public void shouldFindPasswordForTestRoomName() {
        DoorPasswordCracker cracker = new DoorPasswordCracker("abc");
        assertEquals("18f47a30", cracker.getPassword());
    }

    @Test
    @Ignore
    public void shouldFindEnhancedPasswordForTestRoomName() {
        DoorPasswordCracker cracker = new DoorPasswordCracker("abc");
        assertEquals("05ace8e3", cracker.getEnhancedPassword());
    }
}