package aoc.event.year2022;

import aoc.Solver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/// # [2022-01: Calorie Counting](https://adventofcode.com/2022/day/1)
public class Day01 implements Solver<Integer, Integer> {
    
    private final List<Integer> sublistSums;
    
    public Day01(String input) {
        sublistSums = parse(input);
    }
    
    private static List<Integer> parse(String input) {
        var sums = new ArrayList<Integer>();
        int sum = 0;
        for (var line : input.lines().toList()) {
            if (line.isEmpty()) {
                sums.add(sum);
                sum = 0;
            }
            else {
                sum += Integer.parseInt(line);
            }
        }
        sums.add(sum);
        return sums;
    }
    
    /// Calculates the max sublist sum.
    @Override
    public Integer partOne() {
        return sublistSums
            .stream()
            .reduce(0, Integer::max);
    }
    
    /// Calculates the sum of the three largest sublist sums.
    @Override
    public Integer partTwo() {
        return sublistSums
            .stream()
            .sorted(Comparator.reverseOrder())
            .limit(3)
            .mapToInt(Integer::intValue)
            .sum();
    }
}
