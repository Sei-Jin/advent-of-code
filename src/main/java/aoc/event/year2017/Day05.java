package aoc.event.year2017;

import aoc.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/// --- Day 5: A Maze of Twisty Trampolines, All Alike ---
public class Day05 implements Solver<Integer> {
    
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
    
    private static void updateInstruction(List<Integer> instructions, int instruction, int change) {
        instructions.set(instruction, instructions.get(instruction) + change);
    }
    
    /// @return the number of steps it takes to reach the exit.
    @Override
    public Integer partOne() {
        final var instructionsCopy = new ArrayList<>(instructions);
        
        int steps = 0;
        int nextInstruction = instructionsCopy.getFirst();
        
        while (nextInstruction >= 0 && nextInstruction < instructionsCopy.size()) {
            int previousInstruction = nextInstruction;
            int jumpOffset = instructionsCopy.get(nextInstruction);
            nextInstruction += jumpOffset;
            updateInstruction(instructionsCopy, previousInstruction, 1);
            steps++;
        }
        
        return steps;
    }
    
    /// @return the number of steps it now takes to reach the exit.
    @Override
    public Integer partTwo() {
        final var instructionsCopy = new ArrayList<>(instructions);
        
        int steps = 0;
        int nextInstruction = instructionsCopy.getFirst();
        
        while (nextInstruction >= 0 && nextInstruction < instructionsCopy.size()) {
            int previousInstruction = nextInstruction;
            int jumpOffset = instructionsCopy.get(nextInstruction);
            nextInstruction += jumpOffset;
            
            if (jumpOffset >= 3) {
                updateInstruction(instructionsCopy, previousInstruction, -1);
            } else {
                updateInstruction(instructionsCopy, previousInstruction, 1);
            }
            
            steps++;
        }
        
        return steps;
    }
}
