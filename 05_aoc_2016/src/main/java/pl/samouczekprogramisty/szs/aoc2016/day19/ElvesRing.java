package pl.samouczekprogramisty.szs.aoc2016.day19;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class ElvesRing {
    private final int ringSize;

    public ElvesRing(int ringSize) {
        this.ringSize = ringSize;
    }

    public int findLastThief() {
        int elvesLeft = ringSize;
        int currentPower = 2;
        int firstElfInCurrentIteration = 1;

        while (currentPower < ringSize) {
            if (elvesLeft % 2 != 0) {
                firstElfInCurrentIteration += currentPower;
            }
            elvesLeft /= 2;
            currentPower *= 2;
        }
        return firstElfInCurrentIteration;
    }

    private static class CircularBuffer {
        private final List<Integer> buffer;

        public CircularBuffer(int capacity) {
            buffer = new LinkedList<>();
            for (int elfNumber = 1; elfNumber <= capacity; elfNumber++) {
                buffer.add(elfNumber);
            }
        }

        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                private Iterator<Integer> iterator = buffer.iterator();

                @Override
                public boolean hasNext() {
                    return buffer.size() != 0;
                }

                @Override
                public Integer next() {
                    if (iterator.hasNext()) {
                        return iterator.next();
                    }
                    if (hasNext()) {
                        iterator = buffer.iterator();
                        return iterator.next();
                    }
                    throw new NoSuchElementException();
                }

                @Override
                public void remove() {
                    iterator.remove();
                }
            };
        }

        @Override
        public String toString() {
            return buffer.toString();
        }

        public int size() {
            return buffer.size();
        }
    }

    public int findLastThiefInFont() {
        CircularBuffer elvesRing = new CircularBuffer(ringSize);
        Iterator<Integer> iterator = elvesRing.iterator();

        int skipElves = ringSize / 2;
        while (skipElves > 0) {
            iterator.next();
            skipElves--;
        }

        if (ringSize % 2 == 1) {
            iterator.next();
            iterator.remove();

            iterator.next();
        }

        while (elvesRing.size() > 1) {
            if (elvesRing.size() > 1) {
                iterator.next();
                iterator.remove();
            }

            if (elvesRing.size() > 1) {
                iterator.next();
                iterator.remove();
            }

            iterator.next();
        }

        return iterator.next();
    }
}

