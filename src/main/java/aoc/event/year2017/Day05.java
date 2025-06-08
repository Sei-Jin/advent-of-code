package aoc.event.year2017;

import aoc.Solver;

import java.util.ArrayList;
import java.util.List;

/// # [2017-05: A Maze of Twisty Trampolines, All Alike](https://adventofcode.com/2017/day/5)
public class Day05 implements Solver<Integer, Integer> {
    
    private static List<Integer> instructions;
    
    public Day05(String input) {
        instructions = parse(input);
    }
    
    private static List<Integer> parse(String input) {
        return input
            .lines()
            .map(Integer::parseInt)
            .toList();
    }
    
    @Override
    public Integer partOne() {
        return calculateSteps(instructions, false);
    }
    
    @Override
    public Integer partTwo() {
        return calculateSteps(instructions, true);
    }
    
    private static int calculateSteps(List<Integer> instructions, boolean partTwo) {
        var list = new ArrayList<>(instructions);
        var steps = 0;
        var next = list.getFirst();
        
        while (next >= 0 && next < list.size()) {
            int previous = next;
            var jumpOffset = list.get(next);
            
            if (partTwo && jumpOffset >= 3) {
                list.set(previous, list.get(previous) - 1);
            }
            else {
                list.set(previous, list.get(previous) + 1);
            }
            next += jumpOffset;
            steps++;
        }
        return steps;
    }
}
