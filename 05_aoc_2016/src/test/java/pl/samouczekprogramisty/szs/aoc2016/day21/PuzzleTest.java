package pl.samouczekprogramisty.szs.aoc2016.day21;


import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PuzzleTest {
    @Test
    public void shouldSolvePuzzle() {
        PasswordScrambler scrambler = new PasswordScrambler("abcdefgh");
        List<String> instructions = InputFileReader.readFileAsLines("day21_input.txt");
        assertEquals("gbhcefad", scrambler.applyInstructions(instructions).getPassword());
    }

    @Test
    public void shouldSolvePuzzle2() {
        String desired = "fbgdceah";
        String searchedPermutation = null;
        List<String> instructions = InputFileReader.readFileAsLines("day21_input.txt");
        for (String permutation : permutations("abcdefgh")) {
            searchedPermutation = permutation;
            PasswordScrambler scrambler = new PasswordScrambler(permutation);
            scrambler.applyInstructions(instructions);
            if (scrambler.getPassword().equals(desired)) {
                break;
            }
        }
        assertEquals("gahedfcb", searchedPermutation);
    }

    public List<String> permutations(String input) {
        List<String> permutations = new LinkedList<>();
        permutations(permutations, "", input);
        return permutations;
    }

    private void permutations(List<String> accumulator, String currentPermutation, String leftToMakePermutation) {
        int length = leftToMakePermutation.length();
        if (length == 0) {
            accumulator.add(currentPermutation);
        }
        for (int index = 0; index < length; index++) {
            permutations(
                    accumulator,
                    currentPermutation + leftToMakePermutation.charAt(index),
                    leftToMakePermutation.substring(0, index) + leftToMakePermutation.substring(index + 1, length)
            );
        }
    }
}
