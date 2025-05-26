package aoc.event.year2024;

import aoc.Solver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Gatherers;

/// # [2024-02: Red-Nosed Reports](https://adventofcode.com/2024/day/2)
public class Day02 implements Solver<Integer, Integer> {
    
    private final List<List<Integer>> reports;
    
    public Day02(String input) {
        reports = parse(input);
    }
    
    private static List<List<Integer>> parse(String input) {
        return input
            .lines()
            .map(line ->
                Arrays.stream(line.split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList())
            .toList();
    }
    
    @Override
    public Integer partOne() {
        return (int) reports
            .stream()
            .filter(report -> {
                var allIncreasing = report
                    .stream()
                    .gather(Gatherers.windowSliding(2))
                    .noneMatch(list -> list.get(0) >= list.get(1));
                
                var allDecreasing = report
                    .stream()
                    .gather(Gatherers.windowSliding(2))
                    .noneMatch(list -> list.get(0) <= list.get(1));
                
                var safeDifference = report
                    .stream()
                    .gather(Gatherers.windowSliding(2))
                    .noneMatch(list -> {
                        var difference = Math.abs(list.get(0) - list.get(1));
                        return (difference < 1 || difference > 3);
                    });
                
                return (allDecreasing || allIncreasing) && safeDifference;
            })
            .count();
    }
    
    @Override
    public Integer partTwo() {
        return 0;
    }
}
