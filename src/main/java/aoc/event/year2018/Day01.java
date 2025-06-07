package aoc.event.year2018;

import aoc.Solver;

import java.util.HashSet;
import java.util.List;

/// # [2018-01: Chronal Calibration](https://adventofcode.com/2018/day/1)
public class Day01 implements Solver<Integer, Integer> {
    
    private static final int STARTING_FREQUENCY = 0;
    private final List<Integer> frequencies;
    
    public Day01(String input) {
        frequencies = parse(input);
    }
    
    private static List<Integer> parse(String input) {
        return input
            .lines()
            .map(line -> {
                var sign = line.charAt(0);
                var change = Integer.parseInt(line.substring(1));
                if (sign == '-') {
                    change *= -1;
                }
                return change;
            })
            .toList();
    }
    
    /// Calculates the resulting frequency after all frequency changes are applied.
    @Override
    public Integer partOne() {
        return frequencies
            .stream()
            .reduce(STARTING_FREQUENCY, Integer::sum);
    }
    
    /// Finds the first frequency reached twice.
    ///
    /// If no duplicate frequency is found then the changes will be applied again until a
    /// duplicate frequency is found.
    @Override
    public Integer partTwo() {
        int current = STARTING_FREQUENCY;
        var seen = new HashSet<Integer>();
        seen.add(STARTING_FREQUENCY);
        while (true) {
            for (int change : frequencies) {
                current += change;
                if (!seen.contains(current)) {
                    seen.add(current);
                } else {
                    return current;
                }
            }
        }
    }
}
