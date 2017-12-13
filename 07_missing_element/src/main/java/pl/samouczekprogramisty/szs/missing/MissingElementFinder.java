package pl.samouczekprogramisty.szs.missing;

public class MissingElementFinder {

    public static int findMissing(int... array) {
        return optimalFindMissing(array);
    }

    protected static int naiveFindMissing(int... array) {
        int missing = 0;
        boolean elementFound;
        for (int elementToFind = 0; elementToFind <= array.length; elementToFind++) {
            elementFound = false;
            for (int elementInArray : array) {
                if (elementToFind == elementInArray) {
                    elementFound = true;
                    break;
                }
            }
            if (!elementFound) {
                missing = elementToFind;
                break;
            }
        }
        return  missing;
    }

    protected static int memoryGreedyFindMissing(int... array) {
        boolean[] foundElements = new boolean[array.length + 1];

        for (int element : array) {
            foundElements[element] = true;
        }

        for (int index = 0; index < foundElements.length; index++) {
            if (!foundElements[index]) {
                return index;
            }
        }
        throw new IllegalStateException("At least one flag should be equal false!");
    }

    protected static int optimalFindMissing(int... array) {
        int expectedSum = (array.length + 1) * array.length / 2;
        int actualSum = 0;

        for (int element : array) {
            actualSum += element;
        }

        return expectedSum - actualSum;
    }

}
