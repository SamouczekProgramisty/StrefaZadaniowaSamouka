package pl.samouczekprogramisty.szs.aoc2016.day16;

public class DragonChecksum {
    private String sequence;
    private String checksum;
    private final int desiredSequenceLenght;

    public DragonChecksum(String startingSequence, int desiredSequenceLenght) {
        sequence = startingSequence;
        this.desiredSequenceLenght = desiredSequenceLenght;
        ensureThatSequenceIsLongEnough();
    }

    public String getChecksum() {
        if (checksum == null) {
            checksum = computeChecksum(sequence.substring(0, desiredSequenceLenght));
        }
        return checksum;
    }

    private String computeChecksum(String sequenceToSquash) {
        if (sequenceToSquash.length() % 2 != 0) {
            return sequenceToSquash;
        }
        StringBuilder checksum = new StringBuilder();

        char[] characters = sequenceToSquash.toCharArray();
        for (int characterIndex = 0; characterIndex < sequenceToSquash.length(); characterIndex += 2) {
            if (characters[characterIndex] == characters[characterIndex + 1]) {
                checksum.append('1');
            }
            else {
                checksum.append('0');
            }
        }

        return computeChecksum(checksum.toString());
    }

    public String getSequence() {
        return sequence;
    }

    private void ensureThatSequenceIsLongEnough() {
        while (sequence.length() < desiredSequenceLenght) {
            String newPart = reverseAndFlip(sequence);
            sequence = sequence + "0" + newPart;
        }
    }

    private String reverseAndFlip(String sequence) {
        StringBuilder newSequence = new StringBuilder();
        char[] characters = sequence.toCharArray();
        for (int currentCharacter = characters.length - 1; currentCharacter >= 0; currentCharacter--) {
            if (characters[currentCharacter] == '1') {
                newSequence.append('0');
            }
            else {
                newSequence.append('1');
            }
        }
        return newSequence.toString();
    }
}
