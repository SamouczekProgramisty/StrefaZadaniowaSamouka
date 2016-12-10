package pl.samouczekprogramisty.szs.aoc2016.day10;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class BotOverlordTest {
    private static BotOverlord overlord;

    @BeforeClass
    public static void setUpClass() {
        List<String> instructions = Arrays.asList(
                "value 5 goes to bot 2",
                "bot 2 gives low to bot 1 and high to bot 0",
                "value 3 goes to bot 1",
                "bot 1 gives low to output 1 and high to bot 0",
                "bot 0 gives low to output 2 and high to output 0",
                "value 2 goes to bot 2"
        );

        overlord = BotOverlord.processInstructions(instructions);
    }

    @Test
    public void shouldCollectOutput() {
        assertEquals(Integer.valueOf(5), overlord.getOutput(0).getValue());
        assertEquals(Integer.valueOf(2), overlord.getOutput(1).getValue());
        assertEquals(Integer.valueOf(3), overlord.getOutput(2).getValue());
    }

    @Test
    public void shouldFindAppropriateBot() {
        Bot bot = overlord.getBotResponsibleFor(2, 5);
        assertEquals(Integer.valueOf(2), bot.getId());
    }
}