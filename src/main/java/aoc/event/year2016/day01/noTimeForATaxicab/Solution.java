package aoc.event.year2016.day01.noTimeForATaxicab;

import aoc.Runner;
import aoc.Solver;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

/// --- Day 1: No Time for a Taxicab ---
public class Solution implements Solver {
    
    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("(\\w)(\\d+)");
    
    private final List<Instruction> instructions;
    
    public Solution(String input) {
        instructions = parse(input);
    }
    
    private static List<Instruction> parse(String input) {
        final var instructions = new ArrayList<Instruction>();
        final var matcher = INSTRUCTION_PATTERN.matcher(input);
        
        while (matcher.find()) {
            final var turn = Turn.of(matcher.group(1).charAt(0));
            final var distance = Integer.parseInt(matcher.group(2));
            
            instructions.add(new Instruction(turn, distance));
        }
        
        return instructions;
    }
    
    private record Instruction(Turn turn, int distance) {}
    
    /// @return the number of blocks from the starting position to the Easter Bunny HQ.
    @Override
    public Integer partOne() {
        Point point = new Point();
        Direction currentDirection = Direction.NORTH;
        
        for (final var instruction : instructions) {
            currentDirection = changeDirection(currentDirection, instruction.turn);
            
            switch (currentDirection) {
                case NORTH -> point.y += instruction.distance;
                case EAST -> point.x += instruction.distance;
                case SOUTH -> point.y -= instruction.distance;
                case WEST -> point.x -= instruction.distance;
            }
        }
        
        return Math.abs(point.x) + Math.abs(point.y);
    }
    
    /// @return the distance from the starting location to the first location visited twice or `-1` if there
    ///         were no locations visited twice.
    @Override
    public Integer partTwo() {
        Point point = new Point();
        Direction currentDirection = Direction.NORTH;
        
        HashSet<String> pointsVisited = new HashSet<>();
        pointsVisited.add(point.toString());
        
        for (final var instruction : instructions) {
            currentDirection = changeDirection(currentDirection, instruction.turn);
            
            for (int blocksTravelled = 0; blocksTravelled < instruction.distance; blocksTravelled++) {
                switch (currentDirection) {
                    case NORTH -> point.y++;
                    case EAST -> point.x++;
                    case SOUTH -> point.y--;
                    case WEST -> point.x--;
                }
                
                if (!pointsVisited.contains(point.toString())) {
                    pointsVisited.add(point.toString());
                } else {
                    return Math.abs(point.x) + Math.abs(point.y);
                }
            }
        }
        
        // There were no locations visited twice
        return -1;
    }
    
    private static Direction changeDirection(Direction currentDirection, Turn turningDirection) {
        if (turningDirection == Turn.RIGHT) {
            currentDirection = switch (currentDirection) {
                case NORTH -> Direction.EAST;
                case EAST -> Direction.SOUTH;
                case SOUTH -> Direction.WEST;
                case WEST -> Direction.NORTH;
            };
        }
        
        if (turningDirection == Turn.LEFT) {
            currentDirection = switch (currentDirection) {
                case NORTH -> Direction.WEST;
                case WEST -> Direction.SOUTH;
                case SOUTH -> Direction.EAST;
                case EAST -> Direction.NORTH;
            };
        }
        
        return currentDirection;
    }
    
    private enum Turn {
        RIGHT,
        LEFT;
        
        private static Turn of(char character) {
            return switch (character) {
                case 'R' -> RIGHT;
                case 'L' -> LEFT;
                default -> throw new IllegalStateException("Unexpected value: " + character);
            };
        }
    }
    
    private enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2016, 1);
    }
}
