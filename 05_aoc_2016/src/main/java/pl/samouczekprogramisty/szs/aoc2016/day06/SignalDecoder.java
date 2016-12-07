package pl.samouczekprogramisty.szs.aoc2016.day06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignalDecoder {
    private String mostCommonSignal;
    private String leastCommonSignal;

    public SignalDecoder(List<String> codes) {
        List<Map<Character, Integer>> positionCount = countLettersInCodes(codes);
        findSecretSignal(positionCount);
    }

    private void findSecretSignal(List<Map<Character, Integer>> positionCount) {
        StringBuilder mostCommonBuilder = new StringBuilder();
        StringBuilder leastCommonBuilder = new StringBuilder();

        for (Map<Character, Integer> letterCount : positionCount) {
            mostCommonBuilder.append(findMostCommonLetter(letterCount));
            leastCommonBuilder.append(findLeastCommonLetter(letterCount));
        }

        mostCommonSignal = mostCommonBuilder.toString();
        leastCommonSignal = leastCommonBuilder.toString();
    }

    private Character findMostCommonLetter(Map<Character, Integer> letterCount) {
        return findLetter(letterCount, true);
    }

    private Character findLeastCommonLetter(Map<Character, Integer> letterCount) {
        return findLetter(letterCount, false);
    }

    private Character findLetter(Map<Character, Integer> letterCount, boolean mostCommon) {
        List<Map.Entry<Character, Integer>> letters = new ArrayList<>(letterCount.entrySet());
        Collections.sort(letters, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                int countComparision;
                if (mostCommon) {
                    countComparision = o2.getValue().compareTo(o1.getValue());
                } else {
                    countComparision = o1.getValue().compareTo(o2.getValue());
                }

                if (countComparision == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return countComparision;
            }
        });
        return letters.get(0).getKey();
    }

    private List<Map<Character, Integer>> countLettersInCodes(List<String> codes) {
        List<Map<Character, Integer>> positionCount = initializePositionCount(codes.get(0).length());

        for (String code : codes) {
            char[] letters = code.toCharArray();
            for (int letterIndex = 0; letterIndex < letters.length; letterIndex++) {
                Character letter = letters[letterIndex];
                Map<Character, Integer> letterCount = positionCount.get(letterIndex);
                int count = 1;
                if (letterCount.containsKey(letter)) {
                    count += letterCount.get(letter);
                }
                letterCount.put(letter, count);
            }
        }

        return positionCount;
    }

    private List<Map<Character, Integer>> initializePositionCount(int codeLenght) {
        List<Map<Character, Integer>> positionCount = new ArrayList<>();
        for (int i = 0; i < codeLenght; i++) {
            positionCount.add(new HashMap<>());
        }
        return positionCount;
    }

    public String getMostCommonSignal() {
        return mostCommonSignal;
    }

    public String getLeastCommonSignal() {
        return leastCommonSignal;
    }
}