package pl.samouczekprogramisty.szs.dna;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DNASequencer {
    static boolean naiveChangePossible(String sequence1, String sequence2) {
        List<String> peramutationsCache = new LinkedList<>();
        permutations(peramutationsCache, "", sequence1);
        return peramutationsCache.contains(sequence2);
    }

    private static void permutations(List<String> permutationsCache, String currentPermutation, String leftCharacters) {
        if (leftCharacters.length() == 0) {
            permutationsCache.add(currentPermutation);
        }
        for (int i = 0; i < leftCharacters.length(); i++) {
            permutations(
                permutationsCache,
                currentPermutation + leftCharacters.charAt(i),
                leftCharacters.substring(0, i) + leftCharacters.substring(i + 1)
            );
        }
    }

    static boolean slightlyBetterChangePossible(String sequence1, String sequence2) {
        char[] s1 = sequence1.toCharArray();
        char[] s2 = sequence2.toCharArray();
        Arrays.sort(s1);
        Arrays.sort(s2);
        return Arrays.equals(s1, s2);
    }

    static boolean optimalChangePossible(String sequence1, String sequence2) {
        return Arrays.equals(countLetters(sequence1), countLetters(sequence2));
    }

    private static int[] countLetters(String sequence) {
        int[] counters = new int['T' - 'A' + 1];

        for (char c : sequence.toCharArray()) {
            counters[c - 'A']++;
        }

        return counters;
    }

    static boolean changePossible(String sequence1, String sequence2) {
        Map<Character, Long> s1Counters = countLettersWithStreams(sequence1);
        Map<Character, Long> s2Counters = countLettersWithStreams(sequence2);
        return s1Counters.equals(s2Counters);
    }

    private static Map<Character, Long> countLettersWithStreams(String sequence1) {
        return sequence1.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            System.out.println(changePossible(line, reader.readLine()));
        }
    }
}
