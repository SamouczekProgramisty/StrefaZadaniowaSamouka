package pl.samouczekprogramisty.szs.aoc2016.day05;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DoorPasswordCracker {
    private static final int PASSWORD_LENGTH = 8;
    public static final String VALID_HASH_PREFIX = "00000";
    public static final char NULL_CHARACTER = '\u0000';

    private final String roomId;
    private String password;
    private String enhancedPassword;

    public DoorPasswordCracker(String roomId) {
        this.roomId = roomId;
    }

    private String crackPassword() {
        StringBuilder passwordBuilder = new StringBuilder(PASSWORD_LENGTH);
        long hashIndex = 0L;
        int passwordCaracterPosition = VALID_HASH_PREFIX.length();
        MessageDigest md5 = getMessageDigest();

        while (passwordBuilder.length() != PASSWORD_LENGTH) {
            md5.reset();
            String roomHash = getHashedRoomIfStartsWithZeros(md5, roomId + hashIndex);
            if (roomHash != null) {
                passwordBuilder.append(roomHash.charAt(passwordCaracterPosition));
            }
            hashIndex++;
        }

        return passwordBuilder.toString();
    }

    private MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String crackEnhancedPassword() {
        char[] enhancedPasswordBuilder = new char[PASSWORD_LENGTH];

        long hashIndex = 0L;
        int foundCharacters = 0;
        MessageDigest md5 = getMessageDigest();

        int charecterIndexPosition = VALID_HASH_PREFIX.length();
        int passwordCharacterPosition = VALID_HASH_PREFIX.length() + 1;

        while (foundCharacters < PASSWORD_LENGTH) {
            md5.reset();
            String roomHash = getHashedRoomIfStartsWithZeros(md5, roomId + hashIndex);

            if (roomHash != null) {
                char indexChar = roomHash.charAt(charecterIndexPosition);
                try {
                    int index = Integer.parseInt(Character.toString(indexChar));
                    boolean indexIsntTooBig = index < PASSWORD_LENGTH;
                    boolean indexWasntAlreadyFilled = enhancedPasswordBuilder[index] == NULL_CHARACTER;
                    if (indexIsntTooBig && indexWasntAlreadyFilled) {
                        enhancedPasswordBuilder[index] = roomHash.charAt(passwordCharacterPosition);
                        foundCharacters++;
                    }
                }
                catch (NumberFormatException e) {
                    // ignoring, it was a letter: a, b, c, d, e or f
                }
            }
            hashIndex++;
        }

        return new String(enhancedPasswordBuilder);
    }

    private String getHashedRoomIfStartsWithZeros(MessageDigest md5, String room) {
        md5.update(room.getBytes());
        byte[] digest = md5.digest();

        // PREMATURE OPTIMISATION ;)
        // here is some magic... that requires some explanation.
        // md5 digest contains 16 bytes (128 bits).
        // Each of those bytes encodes two numbers in range <0, 15>
        // If first byte == 0 that means that first two characters of the md5 sum are 0
        // If second byte == 0 that means that 3rd and 4th characters are 0
        // For the third byte I'm "cleraring" some bits by using bitwise and operator
        byte onlyFirstHalfMask = (byte) 0b11110000;
        boolean startsWithFiveZeros = digest[0] == 0 && digest[1] == 0 && (digest[2] & onlyFirstHalfMask) == 0;

        // With the optimisation applied it takes ~15s on my machine to solve the puzzle twice
        // 14791ms
        // Without it it takes more that 1m30s.
        // 92495ms

        if (startsWithFiveZeros) {
            return String.format("%032x", new BigInteger(1, digest));
        }
        return null;
    }

    public String getPassword() {
        if (password == null) {
            password = crackPassword();
        }
        return password;
    }

    public String getEnhancedPassword() {
        if (enhancedPassword == null) {
            enhancedPassword = crackEnhancedPassword();
        }
        return enhancedPassword;
    }
}
