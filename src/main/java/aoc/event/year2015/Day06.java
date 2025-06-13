package aoc.event.year2015;

import aoc.Solver;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// # [2015-06: Probably a Fire Hazard](https://adventofcode.com/2015/day/6)
public class Day06 implements Solver<Integer, Integer> {
    
    private static final Pattern INSTRUCTION_PATTERN =
        Pattern.compile("([\\w ]+) (\\d+),(\\d+) through (\\d+),(\\d+)");
    private static final int GRID_SIZE = 1000;
    private final List<Instruction> instructions;
    
    public Day06(String input) {
        instructions = parse(input);
    }
    
    private static List<Instruction> parse(String input) {
        return input
            .lines()
            .map(INSTRUCTION_PATTERN::matcher)
            .flatMap(Matcher::results)
            .map(result -> {
                var operation = Operation.parse(result.group(1));
                var minX = Integer.parseInt(result.group(2));
                var minY = Integer.parseInt(result.group(3));
                var maxX = Integer.parseInt(result.group(4));
                var maxY = Integer.parseInt(result.group(5));
                return new Instruction(operation, minX, minY, maxX, maxY);
            })
            .toList();
    }
    
    @Override
    public Integer partOne() {
        var lightGrid = new boolean[GRID_SIZE][GRID_SIZE];
        
        for (var instruction : instructions) {
            for (var row = instruction.minY; row <= instruction.maxY; row++) {
                for (var column = instruction.minX; column <= instruction.maxX; column++) {
                    switch (instruction.operation) {
                        case ON -> lightGrid[row][column] = true;
                        case OFF -> lightGrid[row][column] = false;
                        case TOGGLE -> lightGrid[row][column] = !lightGrid[row][column];
                    }
                }
            }
        }
        return sumGrid(lightGrid);
    }
    
    private static int sumGrid(boolean[][] lightGrid) {
        var sum = 0;
        for (var row : lightGrid) {
            for (var column = 0; column < lightGrid[0].length; column++) {
                sum += row[column] ? 1 : 0;
            }
        }
        return sum;
    }
    
    @Override
    public Integer partTwo() {
        var lightGrid = new int[GRID_SIZE][GRID_SIZE];
        
        for (var instruction : instructions) {
            for (var row = instruction.minY; row <= instruction.maxY; row++) {
                for (var column = instruction.minX; column <= instruction.maxX; column++) {
                    switch (instruction.operation) {
                        case ON -> lightGrid[row][column]++;
                        case OFF -> {
                            if (lightGrid[row][column] > 0) {
                                lightGrid[row][column]--;
                            }
                        }
                        case TOGGLE -> lightGrid[row][column] += 2;
                    }
                }
            }
        }
        return sumGrid(lightGrid);
    }
    
    private static int sumGrid(int[][] lightGrid) {
        var sum = 0;
        for (var row : lightGrid) {
            for (var column = 0; column < lightGrid[0].length; column++) {
                sum += row[column];
            }
        }
        return sum;
    }
    
    private record Instruction(Operation operation, int minX, int minY, int maxX, int maxY) {}
    
    private enum Operation {
        
        ON, OFF, TOGGLE;
        
        public static Operation parse(String string) {
            return switch (string) {
                case "turn on" -> ON;
                case "turn off" -> OFF;
                case "toggle" -> TOGGLE;
                default -> throw new IllegalStateException("Unexpected value: " + string);
            };
        }
    }
}
