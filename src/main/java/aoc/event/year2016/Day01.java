package aoc.event.year2016;

import aoc.Solver;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/// # [2016-01: No Time for a Taxicab](https://adventofcode.com/2016/day/1)
public class Day01 implements Solver<Integer, Integer> {
    
    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("(\\w)(\\d+)");
    private final List<Instruction> instructions;
    
    public Day01(String input) {
        instructions = parse(input);
    }
    
    private static List<Instruction> parse(String input) {
        return INSTRUCTION_PATTERN
            .matcher(input)
            .results()
            .map(result -> {
                var turn = parseTurn(result.group(1).charAt(0));
                var distance = Integer.parseInt(result.group(2));
                return new Instruction(turn, distance);
            })
            .toList();
    }
    
    private static Turn parseTurn(char character) {
        return switch (character) {
            case 'R' -> Turn.RIGHT;
            case 'L' -> Turn.LEFT;
            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
    }
    
    /// Calculates the distance from the starting position after all instructions have been
    /// followed.
    @Override
    public Integer partOne() {
        var current = new Point(0, 0);
        var direction = Direction.NORTH;
        
        for (var instruction : instructions) {
            direction = Direction.turn(direction, instruction.turn);
            switch (direction) {
                case NORTH -> current.y += instruction.distance;
                case EAST -> current.x += instruction.distance;
                case SOUTH -> current.y -= instruction.distance;
                case WEST -> current.x -= instruction.distance;
            }
        }
        return Math.abs(current.x) + Math.abs(current.y);
    }
    
    /// Calculates the distance from the starting position to the first position visited twice.
    @Override
    public Integer partTwo() {
        var current = new Point(0, 0);
        var direction = Direction.NORTH;
        
        var seen = new HashSet<Point>();
        seen.add(current);
        
        for (var instruction : instructions) {
            direction = Direction.turn(direction, instruction.turn);
            for (var i = 0; i < instruction.distance; i++) {
                switch (direction) {
                    case NORTH -> current.y++;
                    case EAST -> current.x++;
                    case SOUTH -> current.y--;
                    case WEST -> current.x--;
                }
                if (!seen.contains(current)) {
                    seen.add(new Point(current.x, current.y));
                }
                else {
                    return Math.abs(current.x) + Math.abs(current.y);
                }
            }
        }
        throw new IllegalStateException("There were no points visited twice.");
    }
    
    private enum Turn {
        RIGHT,
        LEFT;
    }
    
    private enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST;
        
        public static Direction turn(Direction direction, Turn turn) {
            return switch (turn) {
                case RIGHT -> switch (direction) {
                    case NORTH -> Direction.EAST;
                    case EAST -> Direction.SOUTH;
                    case SOUTH -> Direction.WEST;
                    case WEST -> Direction.NORTH;
                };
                case LEFT -> switch (direction) {
                    case NORTH -> Direction.WEST;
                    case WEST -> Direction.SOUTH;
                    case SOUTH -> Direction.EAST;
                    case EAST -> Direction.NORTH;
                };
            };
        }
    }
    
    private record Instruction(Turn turn, int distance) {}
    
    private static class Point {
        
        int x;
        int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point point)) return false;
            return x == point.x && y == point.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
