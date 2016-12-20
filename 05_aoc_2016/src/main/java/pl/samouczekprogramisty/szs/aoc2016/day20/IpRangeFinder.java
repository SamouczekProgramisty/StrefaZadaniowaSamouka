package pl.samouczekprogramisty.szs.aoc2016.day20;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class IpRangeFinder {
    private final List<IpRange> ranges;

    public IpRangeFinder(IpRange ... ranges) {
        this.ranges = Arrays.asList(ranges);
        Collections.sort(this.ranges);
    }

    public long getFirstAllowedIp() {
        long firstAllowedIp = 0;
        for (IpRange range : ranges) {
            if (firstAllowedIp < range.start) {
                return firstAllowedIp;
            }
            if (range.end > firstAllowedIp) {
                firstAllowedIp = range.end + 1;
            }
        }
        return firstAllowedIp;
    }

    public long getAllowedIpsCount() {
        long allowedIpCount = 0;
        long allowedIp = 0;
        for (IpRange range : ranges) {
            if (allowedIp < range.start) {
                allowedIpCount += range.start - allowedIp;
            }
            if (range.end > allowedIp) {
                allowedIp = range.end + 1;
            }
        }

        return allowedIpCount;
    }
}