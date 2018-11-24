package pl.samouczekprogramisty.szs.aoc2016.day05;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class PuzzleTest {
    @Test
    @Disabled
    public void shouldFindPasswordForRoom() {
        DoorPasswordCracker cracker = new DoorPasswordCracker("abbhdwsy");
        System.out.println(cracker.getPassword());
        System.out.println(cracker.getEnhancedPassword());
    }
}
