package pl.samouczekprogramisty.szs.aoc2016.day20;

public class IpRange implements Comparable<IpRange> {
    public final long start;
    public final long end;

    public IpRange(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(IpRange other) {
        if (start > other.start) {
            return 1;
        }
        if (start == other.start) {
            return 0;
        }
        return -1;
    }

    public static IpRange parse(String range) {
        String[] parts = range.split("-");
        return new IpRange(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
    }
}
