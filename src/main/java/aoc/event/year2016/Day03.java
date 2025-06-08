package aoc.event.year2016;

import aoc.Solver;

import java.util.Arrays;
import java.util.List;

/// # [2016-03: Squares With Three Sides](https://adventofcode.com/2016/day/3)
public class Day03 implements Solver<Integer, Integer> {
    
    private static final int TRIANGLE_SIDES = 3;
    private final List<List<Integer>> lists;
    
    public Day03(String input) {
        lists = parse(input);
    }
    
    private static List<List<Integer>> parse(String input) {
        return input
            .lines()
            .map(String::stripLeading)
            .map(line -> Arrays
                .stream(line.split("\\s+"))
                .map(Integer::parseInt)
                .toList())
            .toList();
    }
    
    private static boolean isPossibleTriangle(List<Integer> sideLengths) {
        if (sideLengths.size() != TRIANGLE_SIDES) {
            throw new IllegalArgumentException("There must be exactly 3 sides to form a triangle.");
        }
        var side1 = sideLengths.get(0);
        var side2 = sideLengths.get(1);
        var side3 = sideLengths.get(2);
        return (side1 + side2 > side3) &&
            (side1 + side3 > side2) &&
            (side2 + side3 > side1);
    }
    
    /// Counts the number of possible triangles with sidelengths listed horizontally.
    @Override
    public Integer partOne() {
        return (int) lists
            .stream()
            .filter(Day03::isPossibleTriangle)
            .count();
    }
    
    /// Counts the number of possible triangles with sidelengths listed vertically.
    @Override
    public Integer partTwo() {
        var count = 0;
        for (var x = 0; x < lists.size(); x += TRIANGLE_SIDES) {
            for (var y = 0; y < lists.getFirst().size(); y++) {
                var triangle = List.of(
                    lists.get(x).get(y),
                    lists.get(x + 1).get(y),
                    lists.get(x + 2).get(y)
                );
                if (isPossibleTriangle(triangle)) {
                    count++;
                }
            }
        }
        return count;
    }
}
