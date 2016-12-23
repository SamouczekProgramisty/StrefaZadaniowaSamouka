package pl.samouczekprogramisty.szs.aoc2016.day22;

import org.junit.Test;
import pl.samouczekprogramisty.szs.aoc2016.InputFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NodeTest {
    @Test
    public void shouldBeAbleToCreateNode() {
        Node node = Node.parse("/dev/grid/node-x1-y20   509T  492T    17T   96%");
        assertEquals(1, node.getX());
        assertEquals(20, node.getY());
        assertEquals(509, node.getSize());
        assertEquals(492, node.getUsed());
        assertEquals(17, node.getAvailable());
        assertEquals(96, node.getUsePercentage());
    }

    @Test
    public void shouldSort() throws IOException {
        List<String> lines = InputFileReader.readFileAsLines("day22_input.txt");

        List<Node> nodesBySize = new LinkedList<>();
        for (String line : lines) {
            if (!line.startsWith("/dev")) {
                continue;
            }
            nodesBySize.add(Node.parse(line));
        }

        List<Node> nodesByAvailable = new LinkedList<>(nodesBySize);

        Collections.sort(nodesBySize, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.getUsed() > o2.getUsed()) {
                    return 1;
                }
                if (o1.getUsed() < o2.getUsed()) {
                    return -1;
                }
                return 0;
            }
        });

        Collections.sort(nodesByAvailable, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.getAvailable() > o2.getAvailable()) {
                    return -1;
                }
                if (o1.getAvailable() < o2.getAvailable()) {
                    return 1;
                }
                return 0;
            }
        });

        List<Node[]> eligibleForMove = new ArrayList<>();

        for (Node available : nodesByAvailable) {
            for (Node toMove : nodesBySize) {
                if (toMove.getUsed() == 0) {
                    continue;
                }
                if (available.equals(toMove)) {
                    continue;
                }
                if (available.getAvailable() >= toMove.getUsed()) {
                    eligibleForMove.add(new Node[] {available, toMove});
                }
            }
        }

        assertEquals(990, eligibleForMove.size());
    }
}