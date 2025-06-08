package aoc.event.year2017;

import aoc.Solver;

import java.util.List;
import java.util.regex.Pattern;

/// # [2017-02: Corruption Checksum](https://adventofcode.com/2017/day/2)
public class Day02 implements Solver<Integer, Integer> {
    
    private static final Pattern PATTERN = Pattern.compile("(\\d+)");
    private final List<List<Integer>> numberLists;
    
    public Day02(String input) {
        numberLists = parse(input);
    }
    
    private List<List<Integer>> parse(String input) {
        return input
            .lines()
            .map(line -> PATTERN
                .matcher(line)
                .results()
                .mapToInt(match -> Integer.parseInt(match.group()))
                .boxed()
                .toList())
            .toList();
    }
    
    /// Calculates the sum of the maximum differences for all rows.
    @Override
    public Integer partOne() {
        return numberLists
            .stream()
            .mapToInt(Day02::findMaximumDifference)
            .sum();
    }
    
    private static int findMaximumDifference(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        }
        var min = list.getFirst();
        var max = list.getFirst();
        for (var number : list) {
            min = Math.min(min, number);
            max = Math.max(max, number);
        }
        return max - min;
    }
    
    /// Calculates the sum of the divisible quotients from all rows.
    @Override
    public Integer partTwo() {
        return numberLists
            .stream()
            .mapToInt(Day02::findDivisibleQuotient)
            .sum();
    }
    
    /// Finds the only two numbers in the list that are divisible, and returns their quotient.
    ///
    /// Numbers are divisible if and only if they divide evenly without leaving a remainder.
    private static int findDivisibleQuotient(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i == j) {
                    continue;
                }
                boolean isDivisible = ((list.get(i) % list.get(j)) == 0);
                if (isDivisible) {
                    return list.get(i) / list.get(j);
                }
            }
        }
        throw new IllegalArgumentException("No divisible numbers were found");
    }
}
