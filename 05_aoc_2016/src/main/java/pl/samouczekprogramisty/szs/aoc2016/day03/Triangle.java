package pl.samouczekprogramisty.szs.aoc2016.day03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Triangle {
    private List<Integer> sides = new ArrayList<>(3);

    public Triangle(int sideA, int sideB, int sideC) {
        sides.add(sideA);
        sides.add(sideB);
        sides.add(sideC);
        Collections.sort(sides);
        if (sides.get(0) + sides.get(1) <= sides.get(2)) {
            throw new IllegalArgumentException(String.format("Invalid sides %d, %d, %d!", sideA, sideB, sideC));
        }
    }
}
