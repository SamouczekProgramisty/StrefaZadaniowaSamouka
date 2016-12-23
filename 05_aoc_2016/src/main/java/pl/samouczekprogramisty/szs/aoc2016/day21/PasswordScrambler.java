package pl.samouczekprogramisty.szs.aoc2016.day21;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordScrambler {

    private final char[] password;
    private final Map<Character, Integer> positions = new HashMap<>();

    public PasswordScrambler(String password) {
        this.password = password.toCharArray();
        initializePositions();
    }

    private void initializePositions() {
        for(int index = 0; index < password.length; index++) {
            Character character = password[index];
            positions.put(character, index);
        }
    }

    public PasswordScrambler swapIndices(int index1, int index2) {
        char character1 = password[index1];
        char character2 = password[index2];

        positions.put(character1, index2);
        positions.put(character2, index1);

        password[index2] = character1;
        password[index1] = character2;

        return this;
    }

    public PasswordScrambler swapLetters(char character1, char character2) {
        Integer index1 = positions.get(character1);
        Integer index2 = positions.get(character2);
        return swapIndices(index1, index2);
    }

    public String getPassword() {
        return new String(password);
    }

    public PasswordScrambler reverse(int startIndex, int endIndex) {
        int howManyCharacters = (endIndex - startIndex + 1) / 2;
        for (int index = 0; index < howManyCharacters; index++) {
            swapIndices(startIndex + index, endIndex - index);
        }
        return this;
    }

    public PasswordScrambler rotateLeft(int howManyTimes) {
        howManyTimes = howManyTimes % password.length;
        return rotate(howManyTimes * -1);
    }

    public PasswordScrambler rotateRight(int howManyTimes) {
        return rotate(howManyTimes);
    }

    private PasswordScrambler rotate(int howManyTimes) {
        char[] copy = Arrays.copyOf(password, password.length);
        for(int index = 0; index < password.length; index++) {
            int newIndex = (index + password.length + howManyTimes) % password.length;
            password[newIndex] = copy[index];
        }
        initializePositions();
        return this;
    }

    public PasswordScrambler move(int from, int to) {
        char letterToMove = password[from];
        if (from < to) {
            for (int index = from; index < to; index++) {
                password[index] = password[index + 1];
            }
        }
        else {
            for (int index = from; index > to; index--) {
                password[index] = password[index - 1];
            }
        }
        password[to] = letterToMove;
        initializePositions();
        return this;
    }

    public PasswordScrambler rotateByLetter(char character) {
        int position = positions.get(character);
        int rotation = position + 1;

        if (position >= 4) {
            rotation += 1;
        }

        return rotate(rotation);
    }

    private static final Pattern SWAP_POSITION = Pattern.compile("swap position (\\d+) with position (\\d+)");
    private static final Pattern SWAP_LETTER = Pattern.compile("swap letter ([a-z]) with letter ([a-z])");
    private static final Pattern ROTATE = Pattern.compile("rotate (left|right) (\\d+) steps?");
    private static final Pattern ROTATE_BY_LETTER = Pattern.compile("rotate based on position of letter ([a-z])");
    private static final Pattern REVERSE = Pattern.compile("reverse positions (\\d+) through (\\d+)");
    private static final Pattern MOVE = Pattern.compile("move position (\\d+) to position (\\d+)");

    public PasswordScrambler applyInstructions(List<String> instructions) {
        Matcher matcher = null;
        for (String instruction : instructions) {
            matcher = SWAP_POSITION.matcher(instruction);
            if (matcher.matches()) {
                int index1 = Integer.parseInt(matcher.group(1));
                int index2 = Integer.parseInt(matcher.group(2));
                swapIndices(index1, index2);
                continue;
            }
            matcher = SWAP_LETTER.matcher(instruction);
            if (matcher.matches()) {
                char letter1 = matcher.group(1).charAt(0);
                char letter2 = matcher.group(2).charAt(0);
                swapLetters(letter1, letter2);
                continue;
            }
            matcher = ROTATE.matcher(instruction);
            if (matcher.matches()) {
                int steps = Integer.parseInt(matcher.group(2));
                switch (matcher.group(1)) {
                    case "left":
                        rotateLeft(steps);
                        break;
                    case "right":
                        rotateRight(steps);
                        break;
                }
                continue;
            }
            matcher = ROTATE_BY_LETTER.matcher(instruction);
            if (matcher.matches()) {
                char letter = matcher.group(1).charAt(0);
                rotateByLetter(letter);
                continue;
            }
            matcher = REVERSE.matcher(instruction);
            if (matcher.matches()) {
                int index1 = Integer.parseInt(matcher.group(1));
                int index2 = Integer.parseInt(matcher.group(2));
                reverse(index1, index2);
                continue;
            }
            matcher = MOVE.matcher(instruction);
            if (matcher.matches()) {
                int index1 = Integer.parseInt(matcher.group(1));
                int index2 = Integer.parseInt(matcher.group(2));
                move(index1, index2);
                continue;
            }
            throw new IllegalStateException(instruction);
        }
        return this;
    }
}
