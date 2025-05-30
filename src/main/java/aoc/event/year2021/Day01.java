package aoc.event.year2021;

import aoc.Solver;

import java.util.List;
import java.util.stream.Gatherers;

/// # [2021-01: Sonar Sweep](https://adventofcode.com/2021/day/1)
public class Day01 implements Solver<Integer, Integer> {
    
    private final List<Integer> depths;
    
    public Day01(String input) {
        depths = parse(input);
    }
    
    private static List<Integer> parse(String input) {
        return input
            .lines()
            .map(Integer::valueOf)
            .toList();
    }
    
    /// Calculates the amount of numbers that are larger than the previous in the list.
    @Override
    public Integer partOne() {
        return (int) depths
            .stream()
            .gather(Gatherers.windowSliding(2))
            .filter(window -> window.get(1) > window.get(0))
            .count();
    }
    
    /// Calculates the number of 3-number measurement windows with a larger sum than the previous.
    @Override
    public Integer partTwo() {
        return (int) depths
            .stream()
            .gather(Gatherers.windowSliding(4))
            .filter(window -> {
                var middle = window.get(1) + window.get(2);
                var previousSum = window.get(0) + middle;
                var nextSum = middle + window.get(3);
                return nextSum > previousSum;
            })
            .count();
    }
}
