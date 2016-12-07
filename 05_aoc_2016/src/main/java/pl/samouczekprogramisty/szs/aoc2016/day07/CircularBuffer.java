package pl.samouczekprogramisty.szs.aoc2016.day07;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class CircularBuffer {
    private final int capacity;
    private final Deque<Character> items;

    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        items = new ArrayDeque<>(capacity);
    }

    public void add(Character item) {
        items.addFirst(item);
        if (items.size() > capacity) {
            items.removeLast();
        }
    }

    public List<Character> getItems() {
        List<Character> returnValue = new ArrayList<>();
        Iterator<Character> iterator = items.descendingIterator();
        while(iterator.hasNext()) {
            returnValue.add(iterator.next());
        }
        return returnValue;
    }

    public boolean isFullyLoaded() {
        return items.toArray().length == capacity;
    }

    @Override
    public String toString() {
        return Arrays.toString(items.toArray());
    }
}
