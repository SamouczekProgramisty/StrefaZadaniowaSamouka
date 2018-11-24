package pl.samouczekprogramisty.szs.aoc2016.day06;

import org.junit.jupiter.api.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldFindSecretSignal() {
        List<String> input = InputFileReader.readFileAsLines("day06_input.txt");
        SignalDecoder decoder = new SignalDecoder(input);
        assertEquals("umcvzsmw", decoder.getMostCommonSignal());
        assertEquals("rwqoacfz", decoder.getLeastCommonSignal());
    }
}