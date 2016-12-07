package pl.samouczekprogramisty.szs.aoc2016.day07;

import javax.sql.rowset.spi.SyncProvider;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class IPv7 {
    private final String ipv7;
    private final List<String> standardParts = new LinkedList<>();
    private final List<String> hypernetParts = new LinkedList<>();

    public IPv7(String ipv7) {
        this.ipv7 = ipv7;
        parseIP();
    }

    private interface SequenceFinder {
        CircularBuffer getBuffer();
        String getSequence(CircularBuffer buffer);

        default String joinSequence(CircularBuffer buffer) {
            StringBuilder sb = new StringBuilder();
            for(Character character : buffer.getItems()) {
                sb.append(character);
            }
            return sb.toString();
        }
    }

    private void parseIP() {
        StringBuilder sb = new StringBuilder();
        for (char letter : ipv7.toCharArray()) {
            if (letter == '[') {
                standardParts.add(sb.toString());
                sb = new StringBuilder();
                continue;
            }
            if (letter == ']') {
                hypernetParts.add(sb.toString());
                sb = new StringBuilder();
                continue;
            }
            sb.append(letter);
        }
        if (sb.length() > 0) {
            standardParts.add(sb.toString());
        }
    }

    public boolean supportsTLS() {
        SequenceFinder tlsFinder = new SequenceFinder() {
            private static final int SEQUENCE_LENGHT = 4;

            @Override
            public CircularBuffer getBuffer() {
                return new CircularBuffer(SEQUENCE_LENGHT);
            }

            @Override
            public String getSequence(CircularBuffer buffer) {
                List<Character> chars = buffer.getItems();
                char firstLetter = chars.get(0);
                char secondLetter = chars.get(1);
                char thirdLetter = chars.get(2);
                char fourthLetter = chars.get(3);

                boolean isValidTLS = firstLetter == fourthLetter &&
                        secondLetter == thirdLetter &&
                        firstLetter != secondLetter;

                if (isValidTLS) {
                    return joinSequence(buffer);
                }

                return null;
            }
        };

        if (findSequences(hypernetParts, tlsFinder).size() > 0) {
            return false;
        }

        return findSequences(standardParts, tlsFinder).size() > 0;
    }

    public boolean supportsSSL() {
        SequenceFinder sslFinder = new SequenceFinder() {
            public static final int SEQUENCE_LENGHT = 3;

            @Override
            public CircularBuffer getBuffer() {
                return new CircularBuffer(SEQUENCE_LENGHT);
            }

            @Override
            public String getSequence(CircularBuffer buffer) {
                List<Character> chars = buffer.getItems();
                char firstLetter = chars.get(0);
                char secondLetter = chars.get(1);
                char thirdLetter = chars.get(2);

                if (firstLetter == thirdLetter && firstLetter != secondLetter) {
                    return joinSequence(buffer);
                }

                return null;
            }
        };
        List<String> hypernetSequences = findSequences(hypernetParts, sslFinder);
        Set<String> transformedSequences = transformHypernetToStandard(hypernetSequences);

        List<String> standardSequences = findSequences(standardParts, sslFinder);

        for (String standardSequence : standardSequences) {
            if (transformedSequences.contains(standardSequence)) {
                return true;
            }
        }

        return false;
    }

    private Set<String> transformHypernetToStandard(List<String> hypernetSequences) {
        Set<String> returnValue = new HashSet<>();

        for (String hypernetSequence : hypernetSequences) {
            StringBuilder sb = new StringBuilder();
            sb.append(hypernetSequence.charAt(1));
            sb.append(hypernetSequence.charAt(0));
            sb.append(hypernetSequence.charAt(1));
            returnValue.add(sb.toString());
        }

        return returnValue;
    }

    private List<String> findSequences(List<String> parts, SequenceFinder finder) {
        List<String> sequences = new LinkedList<>();

        for (String part : parts) {
            CircularBuffer buffer = finder.getBuffer();
            for (char letter : part.toCharArray()) {
                buffer.add(letter);
                if (buffer.isFullyLoaded()) {
                    String sequence = finder.getSequence(buffer);
                    if (sequence != null) {
                        sequences.add(sequence);
                    }
                }
            }
        }

        return sequences;
    }
}
