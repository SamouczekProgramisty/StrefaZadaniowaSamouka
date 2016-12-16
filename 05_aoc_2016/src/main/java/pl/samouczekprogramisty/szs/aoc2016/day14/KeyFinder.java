package pl.samouczekprogramisty.szs.aoc2016.day14;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyFinder {
    private static final Pattern REPEATED_3_TIMES = Pattern.compile("([a-f0-9])\\1\\1");
    private static final Pattern REPEATED_5_TIMES = Pattern.compile("([a-f0-9])\\1\\1\\1\\1");
    public static final int REPEAT_HOW_MANY_TIMES = 5;

    private final String salt;
    private final MessageDigest digest;

    public KeyFinder(String salt) {
        this.salt = salt;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static class KeyMetadata implements Comparable<KeyMetadata> {
        private final String tokenThatMustOccurInNextKeys;
        public final String key;
        public final int hashIndex;
        public KeyMetadata tokenOccured;

        public KeyMetadata(String key, int hashIndex, String tokenThatMustOccurInNextKeys) {
            this.key = key;
            this.hashIndex = hashIndex;
            this.tokenThatMustOccurInNextKeys = tokenThatMustOccurInNextKeys;
        }

        @Override
        public String toString() {
            return key + " (" + hashIndex + ") " + "[" + tokenOccured + "]";
        }

        @Override
        public int compareTo(KeyMetadata other) {
            if (hashIndex == other.hashIndex) {
                return 0;
            }
            if (hashIndex < other.hashIndex) {
                return -1;
            }
            return 1;
        }
    }

    private static class Keys {
        private static final int RANGE = 1000;

        private final Map<String, List<KeyMetadata>> potentialKeys = new HashMap<>();
        private final List<KeyMetadata> validKeys = new LinkedList<>();

        public void addPotentialKey(KeyMetadata potentialKey) {
            Matcher matcher = REPEATED_5_TIMES.matcher(potentialKey.key);
            if (matcher.find()) {
                findHashesAndPruneIllegalOnes(potentialKey);
            }

            String tokenThatMustOccurInNextKeys = potentialKey.tokenThatMustOccurInNextKeys;
            if (!potentialKeys.containsKey(tokenThatMustOccurInNextKeys)) {
                potentialKeys.put(tokenThatMustOccurInNextKeys, new LinkedList<>());
            }
            potentialKeys.get(tokenThatMustOccurInNextKeys).add(potentialKey);
        }

        private void findHashesAndPruneIllegalOnes(KeyMetadata currentKey) {
            String repeatedSequence = currentKey.tokenThatMustOccurInNextKeys;
            int currentIndex = currentKey.hashIndex;

            Iterator<KeyMetadata> iterator = potentialKeys.get(repeatedSequence).iterator();
            while (iterator.hasNext()) {
                KeyMetadata keyMetadata = iterator.next();
                boolean isWithinRange = currentIndex <= keyMetadata.hashIndex + RANGE;
                if (isWithinRange) {
                    validKeys.add(keyMetadata);
                    keyMetadata.tokenOccured = currentKey;
                }
                iterator.remove();
            }
        }

        public List<KeyMetadata> getValidKeys() {
            Collections.sort(validKeys);
            return Collections.unmodifiableList(validKeys);
        }
    }

    private interface KeyGenerator {
        String generateKey(int keyIndex);
    }

    public long getIndexOfKey(int key) {
        List<KeyMetadata> keys = getAtLeastNKeys(key, new KeyGenerator() {
            @Override
            public String generateKey(int keyIndex) {
                return getMD5Sum(keyIndex);
            }
        });
        return keys.get(key - 1).hashIndex;
    }

    public long getIndexOfEnhancedKey(int key) {
        List<KeyMetadata> keys = getAtLeastNKeys(key, new KeyGenerator() {
            @Override
            public String generateKey(int keyIndex) {
                return getEnhancedMD5Sum(keyIndex);
            }
        });
        return keys.get(key - 1).hashIndex;
    }

    private List<KeyMetadata> getAtLeastNKeys(int howMany, KeyGenerator generator) {
        int currentIndex = 0;
        Keys keys = new Keys();
        List<KeyMetadata> validKeys = Collections.EMPTY_LIST;

        while (validKeys.size() < howMany) {
            String potentialKey = generator.generateKey(currentIndex);
            Matcher matcher = REPEATED_3_TIMES.matcher(potentialKey);
            if (matcher.find()) {
                String searchFor = repeat5Times(matcher.group(1));
                keys.addPotentialKey(new KeyMetadata(potentialKey, currentIndex, searchFor));
                validKeys = keys.getValidKeys();
            }
            currentIndex++;
        }

        return validKeys;
    }

    private static String repeat5Times(String charToRepeat) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < REPEAT_HOW_MANY_TIMES; i++) {
            sb.append(charToRepeat);
        }
        return sb.toString();
    }

    private String getMD5Sum(int currentIndex) {
        digest.reset();
        digest.update((salt + currentIndex).getBytes());
        return String.format("%032x", new BigInteger(1, digest.digest()));
    }

    private String getEnhancedMD5Sum(int currentIndex) {
        String md5Sum = salt + currentIndex;
        int whichHash = 0;
        while (whichHash <= 2016) {
            digest.reset();
            digest.update(md5Sum.getBytes());
            md5Sum = String.format("%032x", new BigInteger(1, digest.digest()));
            whichHash++;
        }
        return md5Sum;
    }
}