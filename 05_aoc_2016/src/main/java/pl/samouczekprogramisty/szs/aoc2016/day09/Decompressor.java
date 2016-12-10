package pl.samouczekprogramisty.szs.aoc2016.day09;


import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decompressor {
    private static final Pattern REPETITION_PATTERN = Pattern.compile("\\((\\d+)x(\\d+)\\)");

    private Decompressor() {
    }

    private static class RepetitionPair {
        private final Long howMany;
        private final String what;

        public RepetitionPair(Long howMany, String what) {
            this.howMany = howMany;
            this.what = what;
        }

        public Long getHowMany() {
            return howMany;
        }

        public String getWhat() {
            return what;
        }
    }

    public static String decompressV1(String toDecompress) {
        return decompress(toDecompress, false);
    }

    public static String decompressV2(String toDecompress) {
        return decompress(toDecompress, true);
    }

    public static long decompressV2Lenght(String valueToDecompress) {
        Stack<RepetitionPair> toDecompress = new Stack<>();
        toDecompress.push(new RepetitionPair(1L, valueToDecompress));

        long decompressed = 0L;
        RepetitionPair currentItem;
        while (!toDecompress.isEmpty()) {
            currentItem = toDecompress.pop();

            String what = currentItem.getWhat();
            Long howMany = currentItem.getHowMany();

            Matcher matcher = REPETITION_PATTERN.matcher(what);

            if (matcher.find()) {
                decompressed += howMany * matcher.start();

                int howMuchToSkip = matcher.end();
                int howManyCharactersToRepeat = Integer.parseInt(matcher.group(1));
                int repeatHowManyTimes = Integer.parseInt(matcher.group(2));
                int whereEndsRepetitionTagAndRepeatedString = howMuchToSkip + howManyCharactersToRepeat;

                String stillNeedsToBeProcessed = what.substring(whereEndsRepetitionTagAndRepeatedString);
                toDecompress.push(new RepetitionPair(howMany, stillNeedsToBeProcessed));

                String substringToRepeat = what.substring(howMuchToSkip, whereEndsRepetitionTagAndRepeatedString);
                toDecompress.push(new RepetitionPair(howMany * repeatHowManyTimes, substringToRepeat));
            }
            else {
                decompressed += howMany * what.length();
            }
        }
        return decompressed;
    }

    private static String decompress(String valueToDecompress, boolean shouldUseV2) {
        Stack<String> toDecompress = new Stack<>();
        toDecompress.push(valueToDecompress);

        StringBuilder decompressed = new StringBuilder();
        String currentItem;
        while (!toDecompress.isEmpty()) {
            currentItem = toDecompress.pop();
            Matcher matcher = REPETITION_PATTERN.matcher(currentItem);

            if (matcher.find()) {
                String partBeforRepetitionTag = currentItem.substring(0, matcher.start());
                decompressed.append(partBeforRepetitionTag);

                int howMuchToSkip = matcher.end();
                int howManyCharactersToRepeat = Integer.parseInt(matcher.group(1));
                int repeatHowManyTimes = Integer.parseInt(matcher.group(2));
                int whereEndsRepetitionTagAndRepeatedString = howMuchToSkip + howManyCharactersToRepeat;

                String stillNeedsToBeProcessed = currentItem.substring(whereEndsRepetitionTagAndRepeatedString);
                toDecompress.push(stillNeedsToBeProcessed);
                String substringToRepeat = currentItem.substring(howMuchToSkip, whereEndsRepetitionTagAndRepeatedString);
                String repeatedString = repeatString(repeatHowManyTimes, substringToRepeat);
                if (shouldUseV2) {
                    toDecompress.push(repeatedString);
                }
                else {
                    decompressed.append(repeatedString);
                }
            }
            else {
                decompressed.append(currentItem);
            }
        }
        return decompressed.toString();
    }

    private static String repeatString(int howMany, String what) {
        StringBuilder repeated = new StringBuilder();
        while (howMany > 0) {
            repeated.append(what);
            howMany--;
        }
        return repeated.toString();
    }
}
