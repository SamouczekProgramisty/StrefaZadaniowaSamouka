package pl.samouczekprogramisty.szs.aoc2016.day04;

import pl.samouczekprogramisty.szs.aoc2016.day04.exception.InvalidRoomNameException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RoomNameDecoder {
    private static final Pattern ROOM_NAME_PATTERN = Pattern.compile("((?:[a-z]+-?)+)-(\\d+)\\[([a-z]+)\\]");
    public static final int NUMBER_OF_LETTERS = 26;
    public static final int ASCI_A = 97;
    private final String roomName;
    private final int sectorId;
    private String decodedRoomName;

    public RoomNameDecoder(String encodedRoomName) {
        Matcher matcher = ROOM_NAME_PATTERN.matcher(encodedRoomName);
        if (!matcher.matches()) {
            throw new InvalidRoomNameException(encodedRoomName);
        }
        roomName = matcher.group(1);
        sectorId = Integer.parseInt(matcher.group(2));
        validateChecksum(encodedRoomName, matcher.group(3));
        decodeRoomName();
    }

    /**
     * It's a case of Caesar cipher (https://en.wikipedia.org/wiki/Caesar_cipher) used in ancient Rome.
     * Common example of that is ROT13 (https://en.wikipedia.org/wiki/ROT13).
     *
     * In our case number that's used for rotating is sectorId, as we have 26 letters in english alphabet
     * sectorId % 26 is the lowest number that will give use the same result.
     */
    private void decodeRoomName() {
        StringBuilder sb = new StringBuilder(roomName.length());
        int rotNumber = sectorId % NUMBER_OF_LETTERS;

        for (char letter : roomName.toCharArray()) {
            if (letter == '-') {
                sb.append(letter);
            }
            else {
                // here I'm "removing" base,
                // after that step each letter from a to z will be assigned to numbers in range 0, 26
                int newLetterBase = letter % ASCI_A;
                // here I'm rotating the letter
                int rotatedLetter = (newLetterBase + rotNumber) % NUMBER_OF_LETTERS;
                // here I'm adding the base that was "removed" in the first step
                char decodedLetter = (char) (ASCI_A + rotatedLetter);
                sb.append(decodedLetter);
            }
        }
        decodedRoomName = sb.toString();
    }

    private void validateChecksum(String encodedRoomName, String checksum) {
        Map<Character, Integer> letters = countLetters();
        List<Map.Entry<Character, Integer>> sortedLetters = sortByLettersCount(letters);
        String computedChecksum = computeChecksum(sortedLetters);
        if (!checksum.equals(computedChecksum)) {
            throw new InvalidRoomNameException(encodedRoomName + " " + computedChecksum);
        }
    }

    private String computeChecksum(List<Map.Entry<Character, Integer>> entries) {
        int checksumLenght = Math.min(5, entries.size());
        StringBuilder sb = new StringBuilder(checksumLenght);
        for (int i = 0; i < checksumLenght; i ++) {
            sb.append(entries.get(i).getKey());
        }
        return sb.toString();
    }

    private List<Map.Entry<Character, Integer>> sortByLettersCount(Map<Character, Integer> letters) {
        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(letters.entrySet());
        Collections.sort(
                entries,
                new Comparator<Map.Entry<Character, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Character, Integer> count1, Map.Entry<Character, Integer> count2) {
                        // sorting by count descending
                        int countComparision = count2.getValue().compareTo(count1.getValue());
                        // if count is equal we're sorting by letter ascending
                        if (countComparision == 0) {
                            return count1.getKey().compareTo(count2.getKey());
                        }
                        return countComparision;
                    }
                }
        );
        return entries;
    }

    private Map<Character, Integer> countLetters() {
        String roomNameWithoutHyphens = roomName.replaceAll("-", "");
        Map<Character, Integer> letters = new HashMap<>();
        for (Character letter : roomNameWithoutHyphens.toCharArray()) {
            int letterCount = 1;
            if (letters.containsKey(letter)) {
                letterCount += letters.get(letter);
            }
            letters.put(letter, letterCount);
        }
        return letters;
    }

    public int getSectorId() {
        return sectorId;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getDecodedRoomName() {
        return decodedRoomName;
    }
}
