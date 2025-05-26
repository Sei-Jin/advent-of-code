package aoc.event.year2024;

import aoc.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.IntStream;

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
            .filter(Day02::safe)
            .count();
    }
    
    private static boolean safe(List<Integer> report) {
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
    }
    
    @Override
    public Integer partTwo() {
        return (int) reports
            .stream()
            .filter(report ->
                IntStream.range(0, report.size())
                    .anyMatch(i -> {
                        var reportCopy = new ArrayList<>(report);
                        reportCopy.remove(i);
                        return safe(reportCopy);
                    }))
            .count();
    }
}
