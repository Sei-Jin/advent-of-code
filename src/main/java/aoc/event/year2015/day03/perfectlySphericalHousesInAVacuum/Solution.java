package aoc.event.year2015.day03.perfectlySphericalHousesInAVacuum;

import aoc.Runner;
import aoc.Solver;

import java.util.HashSet;
import java.util.Objects;

public class Solution implements Solver {
    
    private final String line;
    
    public Solution(String input) {
        line = input;
    }
    
    /// @return the number of houses that received at least one present.
    @Override
    public Integer partOne() {
        final var current = new Position();
        final var previous = new HashSet<>();
        
        previous.add(current);
        var visited = 1;
        
        for (var i = 0; i < line.length(); i++) {
            updatePosition(current, line.charAt(i));
            
            if (!previous.contains(current)) {
                previous.add(Position.copyOf(current));
                visited++;
            }
        }
        
        return visited;
    }
    
    /// Moves `position` one unit in the given direction.
    ///
    /// @param position  a given position.
    /// @param direction the direction the position be moved in.
    private static void updatePosition(Position position, char direction) {
        switch (direction) {
            case '>' -> position.x++;
            case '<' -> position.x--;
            case '^' -> position.y++;
            case 'v' -> position.y--;
        }
    }
    
    /// @return the number of houses that received at least one present.
    @Override
    public Integer partTwo() {
        final var santa = new Position();
        final var robot = new Position();
        final var previous = new HashSet<Position>();
        
        previous.add(santa);
        var visited = 1;
        
        for (var i = 0; i < line.length(); i++) {
            if (isEven(i)) {
                updatePosition(santa, line.charAt(i));
                
                if (!previous.contains(santa)) {
                    previous.add(Position.copyOf(santa));
                    visited++;
                }
            } else {
                updatePosition(robot, line.charAt(i));
                
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
        
        private static Position copyOf(Position position) {
            return new Position(position.x, position.y);
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
    
    public static void main(String[] args) {
        Runner.runAndPrint(2015, 3);
    }
}
