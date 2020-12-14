package pl.samouczekprogramisty.szs.cyclic;

import java.math.BigInteger;

public final class CyclicNumberFinder {

    private CyclicNumberFinder() { }

    public static boolean isCyclic(String number) {
        return isCyclicGeneration(number);
    }

    static boolean isCyclicNaive(String number) {
        String[] permutations = new String[number.length()];

        for (int index = 0; index < permutations.length; index++) {
            permutations[index] = number.substring(index) + number.substring(0, index);
        }

        BigInteger value = new BigInteger(number);
        String formatString = "%0" + number.length() + "d";

        outerLoop: for (int multiplicator = 2; multiplicator <= number.length(); multiplicator++) {
            BigInteger multiplication = value.multiply(BigInteger.valueOf(multiplicator));
            String formattedResult = String.format(formatString, multiplication);
            for (String permutation : permutations) {
                if (formattedResult.equals(permutation)) {
                    continue outerLoop;
                }
            }
            return false;
        }

        return true;
    }

    static boolean isCyclicGeneration(String number) {
        int base = 10;
        int generatingPrime = number.length() + 1;

        StringBuilder representation = new StringBuilder();

        int step = 0;
        int remainder = 1;
        do {
            step++;
            int currentValueToDivide = remainder * base;
            int currentDigit = currentValueToDivide / generatingPrime;
            remainder = currentValueToDivide % generatingPrime;
            representation.append(currentDigit);
        } while (remainder != 1 && step < generatingPrime);

        return number.equals(representation.toString());
    }

}
