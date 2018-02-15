package pl.samouczekprogramisty.szs.cyclic;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class CyclicNumberFinder {

    private CyclicNumberFinder() { }

    public static boolean isCyclic(String number) {
        return isCyclicNumberPrime(number);
    }

    static boolean isCyclicBruteForce(String number) {
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

    static boolean isCyclicNumberPrime(String number) {
        BigDecimal value = new BigDecimal(number);
        BigDecimal generatingPrime = BigDecimal.valueOf(number.length() + 1);
        BigDecimal oughtToBeNines = value.multiply(generatingPrime);
        return oughtToBeNines.add(BigDecimal.ONE).toString().length() == number.length() + 1;
    }

}
