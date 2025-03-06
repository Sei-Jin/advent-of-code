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
        Position currentPosition = new Position();
        
        HashSet<Position> previousPositions = new HashSet<>();
        
        previousPositions.add(currentPosition);
        int uniqueHousesVisited = 1;
        
        for (int index = 0; index < line.length(); index++) {
            updatePosition(currentPosition, line.charAt(index));
            
            if (!previousPositions.contains(currentPosition)) {
                previousPositions.add(Position.of(currentPosition));
                uniqueHousesVisited++;
            }
        }
        
        return uniqueHousesVisited;
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
        Position currentPositionSanta = new Position();
        Position currentPositionRobot = new Position();
        
        HashSet<Position> previousPositions = new HashSet<>();
        
        previousPositions.add(currentPositionSanta);
        int uniqueHousesVisited = 1;
        
        for (int index = 0; index < line.length(); index++) {
            if (isEven(index)) {
                updatePosition(currentPositionSanta, line.charAt(index));
                
                if (!previousPositions.contains(currentPositionSanta)) {
                    previousPositions.add(Position.of(currentPositionSanta));
                    uniqueHousesVisited++;
                }
            } else {
                updatePosition(currentPositionRobot, line.charAt(index));
                
                if (!previousPositions.contains(currentPositionRobot)) {
                    previousPositions.add(Position.of(currentPositionRobot));
                    uniqueHousesVisited++;
                }
            }
        }
        
        return uniqueHousesVisited;
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
        
        private static Position of(Position position) {
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
