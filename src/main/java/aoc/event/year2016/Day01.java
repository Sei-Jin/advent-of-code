package aoc.event.year2016;

import aoc.DeprecatedSolver2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Day01 implements DeprecatedSolver2 {
    
    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("(\\w)(\\d+)");
    
    private final List<Instruction> instructions;
    
    /// Initializes the solution with the parsed puzzle input.
    ///
    /// The expected format for the puzzle input is a single string of instructions.
    /// - Each instruction is separated by a comma and a space, such as `,`.
    /// - Each instruction consists of a turning direction of left, `L,` or right `R` followed by
    /// number, which is the distance to travel.
    ///
    /// An example input is `R2, L3`.
    public Day01(String input) {
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
    
    /// Calculates the distance from the starting position after all instructions have been
    /// followed.
    ///
    /// @return the distance from the starting position after all instructions have been
    ///     /// followed
    @Override
    public Integer partOne() {
        final var current = new Position();
        var direction = Direction.NORTH;
        
        for (final var instruction : instructions) {
            direction = direction.turn(instruction.turn);
            
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
    ///
    /// @return the distance from the starting position to the first position visited twice, or
    /// -1 if there were no locations visited twice.
    @Override
    public Integer partTwo() {
        final var current = new Position();
        var direction = Direction.NORTH;
        
        final var points = new HashSet<Position>();
        points.add(current);
        
        for (final var instruction : instructions) {
            direction = direction.turn(instruction.turn);
            
            for (var i = 0; i < instruction.distance; i++) {
                switch (direction) {
                    case NORTH -> current.y++;
                    case EAST -> current.x++;
                    case SOUTH -> current.y--;
                    case WEST -> current.x--;
                }
                
                if (!points.contains(current)) {
                    points.add(new Position(current.x, current.y));
                } else {
                    return Math.abs(current.x) + Math.abs(current.y);
                }
            }
        }
        
        return -1;
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
        WEST;
        
        private Direction turn(Turn turn) {
            if (turn == Turn.RIGHT) {
                return switch (this) {
                    case NORTH -> Direction.EAST;
                    case EAST -> Direction.SOUTH;
                    case SOUTH -> Direction.WEST;
                    case WEST -> Direction.NORTH;
                };
            } else if (turn == Turn.LEFT) {
                return switch (this) {
                    case NORTH -> Direction.WEST;
                    case WEST -> Direction.SOUTH;
                    case SOUTH -> Direction.EAST;
                    case EAST -> Direction.NORTH;
                };
            }
            
            return this;
        }
    }
    
    private record Instruction(Turn turn, int distance) {}
    
    private class Position {
        int x;
        int y;
        
        public Position() {
            this.x = 0;
            this.y = 0;
        }
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Position position)) return false;
            return x == position.x && y == position.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
