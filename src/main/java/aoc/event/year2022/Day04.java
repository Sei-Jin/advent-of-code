package aoc.event.year2022;

import aoc.Solver;

import java.util.List;
import java.util.regex.Pattern;

/// # [2022-04: Camp Cleanup](https://adventofcode.com/2022/day/4)
public class Day04 implements Solver<Integer, Integer> {
    
    private static final Pattern RANGE_PATTERN = Pattern.compile(
        "^(\\d+)-(\\d+),(\\d+)-(\\d+)$"
    );
    private final List<RangePair> rangePairs;
    
    public Day04(String input) {
        rangePairs = parse(input);
    }
    
    private static List<RangePair> parse(String input) {
        return input
            .lines()
            .map(line -> {
                var matcher = RANGE_PATTERN.matcher(line);
                if (matcher.find()) {
                    var firstRange = new Range(
                        Integer.parseInt(matcher.group(1)),
                        Integer.parseInt(matcher.group(2))
                    );
                    var secondRange = new Range(
                        Integer.parseInt(matcher.group(3)),
                        Integer.parseInt(matcher.group(4))
                    );
                    return new RangePair(firstRange, secondRange);
                }
                else {
                    throw new IllegalArgumentException(
                        "Error: Unexpected input format encountered with line value: " + line
                    );
                }
            }).toList();
    }
    
    /// Calculates the number of fully contained range pairs.
    @Override
    public Integer partOne() {
        return (int) rangePairs
            .stream()
            .filter(Day04::hasFullyContainedRanges)
            .count();
    }
    
    private static boolean hasFullyContainedRanges(RangePair pair) {
        var first = pair.first;
        var second = pair.second;
        var firstContainsSecond = (first.start <= second.start && first.end >= second.end);
        var secondContainsFirst = (second.start <= first.start && second.end >= first.end);
        return (firstContainsSecond || secondContainsFirst);
    }
    
    /// Calculates the number of overlapping range pairs.
    @Override
    public Integer partTwo() {
        return (int) rangePairs
            .stream()
            .filter(Day04::hasOverlappingRanges)
            .count();
    }
    
    /// The ranges are overlapping if one of them starts before the other ends
    private static boolean hasOverlappingRanges(RangePair pair) {
        var first = pair.first;
        var second = pair.second;
        return (first.start <= second.end && second.start <= first.end);
    }
    
    record Range(int start, int end) {}
    
    record RangePair(Range first, Range second) {}
}
