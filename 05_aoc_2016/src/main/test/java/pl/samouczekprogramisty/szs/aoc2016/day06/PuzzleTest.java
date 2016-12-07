package pl.samouczekprogramisty.szs.aoc2016.day06;

import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;

import java.util.List;

public class PuzzleTest {
    @Test
    public void shouldFindSecretSignal() {
        List<String> input = InputFileReader.readFileAsLines("day06_input.txt");
        SignalDecoder decoder = new SignalDecoder(input);
        System.out.println(decoder.getMostCommonSignal());
        System.out.println(decoder.getLeastCommonSignal());
    }
}