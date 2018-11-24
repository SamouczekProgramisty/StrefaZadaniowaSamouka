package pl.samouczekprogramisty.szs.aoc2016.day10;

import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldSolveTest() {
        List<String> instructions = InputFileReader.readFileAsLines("day10_input.txt");
        BotOverlord overlord = BotOverlord.processInstructions(instructions);
        Bot bot = overlord.getBotResponsibleFor(17, 61);
        assertEquals(Integer.valueOf(118), bot.getId());

        Output output0 = overlord.getOutput(0);
        Output output1 = overlord.getOutput(1);
        Output output2 = overlord.getOutput(2);
        assertEquals(143153, output0.getValue() * output1.getValue() * output2.getValue());
    }
}
