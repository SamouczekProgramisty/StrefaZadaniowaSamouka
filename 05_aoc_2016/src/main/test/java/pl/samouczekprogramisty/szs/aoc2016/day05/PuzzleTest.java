package pl.samouczekprogramisty.szs.aoc2016.day05;

import org.junit.Test;

import java.util.Currency;

public class PuzzleTest {
    @Test
    public void shouldFindPasswordForRoom() {
        DoorPasswordCracker cracker = new DoorPasswordCracker("abbhdwsy");
        long start = System.currentTimeMillis();
        System.out.println(cracker.getPassword());
        System.out.println(cracker.getEnhancedPassword());
        System.out.println(System.currentTimeMillis() - start);
    }
}
