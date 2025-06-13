package aoc.event.year2015;

import aoc.Solver;

import java.util.List;

/// # [2015-01: Not Quite Lisp](https://adventofcode.com/2015/day/1)
public class Day01 implements Solver<Integer, Integer> {
    
    private static List<Instruction> instructions;
    
    public Day01(String input) {
        instructions = parse(input);
    }
    
    private static List<Instruction> parse(String input) {
        return input
            .chars()
            .mapToObj(i -> Instruction.parse((char) i))
            .toList();
    }
    
    @Override
    public Integer partOne() {
        var floorLevel = 0;
        for (var instruction : instructions) {
            switch (instruction) {
                case ASCEND -> floorLevel++;
                case DESCEND -> floorLevel--;
            }
        }
        return floorLevel;
    }
    
    @Override
    public Integer partTwo() {
        var floorLevel = 0;
        for (int i = 0; i < instructions.size(); i++) {
            switch (instructions.get(i)) {
                case ASCEND -> floorLevel++;
                case DESCEND -> floorLevel--;
            }
            var enteredBasement = floorLevel < 0;
            if (enteredBasement) {
                return i + 1;
            }
        }
        throw new IllegalStateException("Santa never entered the basement");
    }
    
    private enum Instruction {
        ASCEND, DESCEND;
        
        public static Instruction parse(char character) {
            return switch (character) {
                case '(' -> ASCEND;
                case ')' -> DESCEND;
                default -> throw new IllegalStateException("Unexpected value: " + character);
            };
        }
    }
}
