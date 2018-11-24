package pl.samouczekprogramisty.szs.aoc2016.day05;

import org.junit.Ignore;
import org.junit.Test;

public class PuzzleTest {
    @Test
    @Ignore
    public void shouldFindPasswordForRoom() {
        DoorPasswordCracker cracker = new DoorPasswordCracker("abbhdwsy");
        System.out.println(cracker.getPassword());
        System.out.println(cracker.getEnhancedPassword());
    }
}
