package aoc.event.year2022;

import aoc.Solver;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Day04 implements Solver {
    
    private static final Pattern RANGE_PATTERN = Pattern.compile(
            "^(\\d+)-(\\d+),(\\d+)-(\\d+)$"
    );
    
    private final List<RangePair> rangePairs;
    
    /// Initializes the solution with the parsed input data.
    ///
    /// The puzzle input contains two ranges on each line, such as `2-4,4-6`. In this example the
    /// first range would be `2-4` and the second range would be `4-6`.
    public Day04(String input) {
        rangePairs = Collections.unmodifiableList(parse(input));
    }
    
    private static List<RangePair> parse(String input) {
        return input.lines().map(line -> {
            final var matcher = RANGE_PATTERN.matcher(line);
            
            if (matcher.find()) {
                final var firstRange = new Range(
                        Integer.parseInt(matcher.group(1)),
                        Integer.parseInt(matcher.group(2))
                );
                
                final var secondRange = new Range(
                        Integer.parseInt(matcher.group(3)),
                        Integer.parseInt(matcher.group(4))
                );
                
                return new RangePair(firstRange, secondRange);
            } else {
                throw new IllegalArgumentException(
                        "Error: Unexpected input format encountered with line value: " + line
                );
            }
        }).toList();
    }
    
    /// Calculates the number of fully contained range pairs.
    public Integer partOne() {
        var count = 0;
        
        for (final var pair : rangePairs) {
            if (hasFullyContainedRanges(pair)) {
                count++;
            }
        }
        
        return count;
    }
    
    private static boolean hasFullyContainedRanges(RangePair pair) {
        final var first = pair.first;
        final var second = pair.second;
        
        final var firstContainsSecond = (first.start <= second.start && first.end >= second.end);
        final var secondContainsFirst = (second.start <= first.start && second.end >= first.end);
        
        return (firstContainsSecond || secondContainsFirst);
    }
    
    /// Calculates the number of overlapping range pairs.
    public Integer partTwo() {
        var count = 0;
        
        for (final var pair : rangePairs) {
            if (hasOverlappingRanges(pair)) {
                count++;
            }
        }
        
        return count;
    }
    
    /// The ranges are overlapping if one of them starts before the other ends
    private static boolean hasOverlappingRanges(RangePair pair) {
        final var first = pair.first;
        final var second = pair.second;
        
        return (first.start <= second.end && second.start <= first.end);
    }
    
    record Range(int start, int end) {}
    
    record RangePair(Range first, Range second) {}
}
