package aoc.event.year2015;

import aoc.DeprecatedSolver2;

import java.util.*;

/// # [2015-03: Perfectly Spherical Houses in a Vacuum](https://adventofcode.com/2015/day/3)
public class Day03 implements DeprecatedSolver2 {
    
    private final List<Direction> directions;
    
    public Day03(String input) {
        directions = Collections.unmodifiableList(parse(input));
    }
    
    private static List<Direction> parse(String input) {
        final var directions = new ArrayList<Direction>();
        
        for (int i = 0; i < input.length(); i++) {
            final var character = input.charAt(i);
            final var direction = Direction.of(character);
            directions.add(direction);
        }
        
        return directions;
    }
    
    /// @return the number of houses that received at least one present.
    @Override
    public Integer partOne() {
        final var current = new Position();
        final var previous = new HashSet<>();
        
        previous.add(current);
        var visited = 1;
        
        for (final var direction : directions) {
            current.increment(direction);
            
            if (!previous.contains(current)) {
                previous.add(Position.copyOf(current));
                visited++;
            }
        }
        
        return visited;
    }
    
    /// @return the number of houses that received at least one present.
    @Override
    public Integer partTwo() {
        final var santa = new Position();
        final var robot = new Position();
        final var previous = new HashSet<Position>();
        
        previous.add(santa);
        var visited = 1;
        
        for (int i = 0; i < directions.size(); i++) {
            final var direction = directions.get(i);
            
            if (isEven(i)) {
                santa.increment(direction);
                
                if (!previous.contains(santa)) {
                    previous.add(Position.copyOf(santa));
                    visited++;
                }
            } else {
                robot.increment(direction);
                
                if (!previous.contains(robot)) {
                    previous.add(Position.copyOf(robot));
                    visited++;
                }
            }
        }
        
        return visited;
    }
    
    /// Determines if a given integer is even or not.
    ///
    /// @param index an integer.
    /// @return true if the number is even, or false otherwise.
    private static boolean isEven(int index) {
        return index % 2 == 0;
    }
    
    private static class Position {
        
        int x;
        int y;
        
        private Position() {
            this.x = 0;
            this.y = 0;
        }
        
        private Position(int x, int y) {
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
        
        /// Moves `position` one unit in the given direction.
        ///
        /// @param direction the direction the position be moved in.
        private void increment(Direction direction) {
            switch (direction) {
                case UP -> y++;
                case DOWN -> y--;
                case LEFT -> x--;
                case RIGHT -> x++;
            }
        }
        
        private static Position copyOf(Position position) {
            return new Position(position.x, position.y);
        }
    }
    
    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
        
        private static Direction of(char character) {
            return switch (character) {
                case '^' -> UP;
                case 'v' -> DOWN;
                case '<' -> LEFT;
                case '>' -> RIGHT;
                default -> throw new IllegalArgumentException("Unexpected value: " + character);
            };
        }
    }
}
