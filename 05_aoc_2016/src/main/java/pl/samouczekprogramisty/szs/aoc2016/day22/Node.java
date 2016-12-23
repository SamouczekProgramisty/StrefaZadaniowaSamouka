package pl.samouczekprogramisty.szs.aoc2016.day22;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Node {
    private final static Pattern NODE_PATTERN =
            Pattern.compile("/dev/grid/node-x(\\d+)-y(\\d+) +(\\d+)T +(\\d+)T +(\\d+)T +(\\d+)%");

    private final int x;
    private final int y;
    private final int size;
    private final int used;
    private final int available;

    private final int usePercentage;

    public Node(int x, int y, int size, int used, int available, int usePercentage) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.used = used;
        this.available = available;
        this.usePercentage = usePercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", size=" + size +
                ", used=" + used +
                ", available=" + available +
                ", usePercentage=" + usePercentage +
                '}';
    }

    public static Node parse(String line) {
        Matcher matcher = NODE_PATTERN.matcher(line);
        if (matcher.matches()) {
            int x = Integer.valueOf(matcher.group(1));
            int y = Integer.valueOf(matcher.group(2));
            int size = Integer.valueOf(matcher.group(3));
            int used = Integer.valueOf(matcher.group(4));
            int available = Integer.valueOf(matcher.group(5));
            int usePercentage = Integer.valueOf(matcher.group(6));

            return new Node(x, y, size, used, available, usePercentage);
        }
        throw new IllegalArgumentException(line);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public int getUsed() {
        return used;
    }

    public int getAvailable() {
        return available;
    }

    public int getUsePercentage() {
        return usePercentage;
    }
}