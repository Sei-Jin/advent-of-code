package aoc.event.year2017;

import aoc.DeprecatedSolver2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/// --- Day 5: A Maze of Twisty Trampolines, All Alike ---
public class Day05 implements DeprecatedSolver2<Integer> {
    
    private static List<Integer> instructions;
    
    public Day05(String input) {
        instructions = Collections.unmodifiableList(parse(input));
    }
    
    private static List<Integer> parse(String input) {
        return input
            .lines()
            .map(Integer::parseInt)
            .toList();
    }
    
    /// @return the number of steps it takes to reach the exit.
    @Override
    public Integer partOne() {
        final var list = new ArrayList<>(instructions);
        
        var steps = 0;
        var next = list.getFirst();
        
        while (next >= 0 && next < list.size()) {
            final int previous = next;
            final var jumpOffset = list.get(next);
            
            list.set(previous, list.get(previous) + 1);
            
            next += jumpOffset;
            steps++;
        }
        
        return steps;
    }
    
    /// @return the number of steps it now takes to reach the exit.
    @Override
    public Integer partTwo() {
        final var list = new ArrayList<>(instructions);
        
        var steps = 0;
        var next = list.getFirst();
        
        while (next >= 0 && next < list.size()) {
            final int previous = next;
            final var jumpOffset = list.get(next);
            
            if (jumpOffset >= 3) {
                list.set(previous, list.get(previous) - 1);
            } else {
                list.set(previous, list.get(previous) + 1);
            }
            
            next += jumpOffset;
            steps++;
        }
        
        return steps;
    }
}
