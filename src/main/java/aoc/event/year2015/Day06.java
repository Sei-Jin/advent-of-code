package aoc.event.year2015;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Day06 implements Solver {
    
    private static final Pattern INSTRUCTION_PATTERN =
        Pattern.compile("([\\w ]+) (\\d+),(\\d+) through (\\d+),(\\d+)");
    
    private static final int GRID_SIZE = 1000;
    
    private final List<Instruction> instructions;
    
    public Day06(String input) {
        instructions = parse(input);
    }
    
    private static List<Instruction> parse(String input) {
        final var instructions = new ArrayList<Instruction>();
        
        for (final var line : input.lines().toList()) {
            final var instruction = parseInstruction(line);
            instructions.add(instruction);
        }
        
        return instructions;
    }
    
    private static Instruction parseInstruction(String line) {
        final var matcher = INSTRUCTION_PATTERN.matcher(line);
        
        if (matcher.find()) {
            final var operation = Operation.of(matcher.group(1));
            final var minX = Integer.parseInt(matcher.group(2));
            final var minY = Integer.parseInt(matcher.group(3));
            final var maxX = Integer.parseInt(matcher.group(4));
            final var maxY = Integer.parseInt(matcher.group(5));
            
            return new Instruction(operation, minX, minY, maxX, maxY);
        } else {
            throw new AssertionError("Line did not meet the expect format" + line);
        }
    }
    
    private static int sumGrid(int[][] lightGrid) {
        var sum = 0;
        
        for (int[] row : lightGrid) {
            for (var column = 0; column < lightGrid[0].length; column++) {
                sum += row[column];
            }
        }
        
        return sum;
    }
    
    /// @return the number of lit lights after following the instructions.
    @Override
    public Integer partOne() {
        final var lightGrid = new int[GRID_SIZE][GRID_SIZE];
        
        for (final var instruction : instructions) {
            for (var row = instruction.minY; row <= instruction.maxY; row++) {
                for (var column = instruction.minX; column <= instruction.maxX; column++) {
                    switch (instruction.operation) {
                        case ON -> lightGrid[row][column] = 1;
                        case OFF -> lightGrid[row][column] = 0;
                        case TOGGLE -> {
                            if (lightGrid[row][column] == 1) {
                                lightGrid[row][column] = 0;
                            } else {
                                lightGrid[row][column] = 1;
                            }
                        }
                    }
                }
            }
        }
        
        return sumGrid(lightGrid);
    }
    
    /// @return the total brightness of all lights combined after following Santa's instructions.
    @Override
    public Integer partTwo() {
        final var lightGrid = new int[GRID_SIZE][GRID_SIZE];
        
        for (final var instruction : instructions) {
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
    
    private record Instruction(Operation operation, int minX, int minY, int maxX, int maxY) {}
    
    private enum Operation {
        ON,
        OFF,
        TOGGLE;
        
        private static Operation of(String string) {
            return switch (string) {
                case "turn on" -> ON;
                case "turn off" -> OFF;
                case "toggle" -> TOGGLE;
                default -> throw new IllegalStateException("Unexpected value: " + string);
            };
        }
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2015, 6);
    }
}
