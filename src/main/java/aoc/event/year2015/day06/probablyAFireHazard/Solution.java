package aoc.event.year2015.day06.probablyAFireHazard;

import aoc.Runner;
import aoc.Solver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Solution implements Solver {
    
    private static final Pattern INSTRUCTION_PATTERN =
        Pattern.compile("([\\w ]+) (\\d+),(\\d+) through (\\d+),(\\d+)");
    
    private final List<Instruction> instructions;
    
    public Solution(String input) {
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
            
            final var min = new Point(minX, minY);
            final var max = new Point(maxX, maxY);
            
            return new Instruction(operation, min, max);
        } else {
            throw new AssertionError("Line did not meet the expect format" + line);
        }
    }
    
    /// @return the number of lit lights after following the instructions.
    @Override
    public Integer partOne() {
        boolean[][] lightGrid = new boolean[1000][1000];
        
        for (final var instruction : instructions) {
            for (int row = instruction.topCorner.y; row <= instruction.bottomCorner.y; row++) {
                for (int column = instruction.topCorner.x; column <= instruction.bottomCorner.x; column++) {
                    switch (instruction.operation) {
                        case ON -> lightGrid[row][column] = true;
                        case OFF -> lightGrid[row][column] = false;
                        case TOGGLE -> lightGrid[row][column] = !lightGrid[row][column];
                    }
                }
            }
        }
        
        int totalLightsTurnedOn = 0;
        
        for (int row = 0; row < lightGrid.length; row++) {
            for (int column = 0; column < lightGrid[0].length; column++) {
                if (lightGrid[row][column]) {
                    totalLightsTurnedOn++;
                }
            }
        }
        
        return totalLightsTurnedOn;
    }
    
    /// @return the total brightness of all lights combined after following Santa's instructions.
    @Override
    public Integer partTwo() {
        int[][] lightGrid = new int[1000][1000];
        
        for (final var instruction : instructions) {
            for (int row = instruction.topCorner.y; row <= instruction.bottomCorner.y; row++) {
                for (int column = instruction.topCorner.x; column <= instruction.bottomCorner.x; column++) {
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
        
        int totalBrightness = 0;
        
        for (int row = 0; row < lightGrid.length; row++) {
            for (int column = 0; column < lightGrid[0].length; column++) {
                totalBrightness += lightGrid[row][column];
            }
        }
        
        return totalBrightness;
    }
    
    private record Instruction(Operation operation, Point topCorner, Point bottomCorner) {}
    
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
