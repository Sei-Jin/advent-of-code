package aoc.event.year2015.day03.perfectlySphericalHousesInAVacuum;

import aoc.DeprecatedSolver;

import java.awt.*;
import java.util.HashSet;
import java.util.List;

public class Solution implements DeprecatedSolver {
    
    /// @param inputLines the puzzle input.
    /// @return the number of houses that received at least one present.
    @Override
    public Object partOne(List<String> inputLines) {
        String inputLine = inputLines.getFirst();
        
        Point currentPosition = new Point();
        
        HashSet<String> previousPositions = new HashSet<>();
        
        previousPositions.add(currentPosition.toString());
        int uniqueHousesVisited = 1;
        
        for (int index = 0; index < inputLine.length(); index++) {
            updatePosition(currentPosition, inputLine.charAt(index));
            
            if (!previousPositions.contains(currentPosition.toString())) {
                previousPositions.add(currentPosition.toString());
                uniqueHousesVisited++;
            }
        }
        
        return uniqueHousesVisited;
    }
    
    /// Moves `position` one unit in the given direction.
    ///
    /// @param position  a given position.
    /// @param direction the direction the position be moved in.
    private static void updatePosition(Point position, char direction) {
        switch (direction) {
            case '>' -> position.x++;
            case '<' -> position.x--;
            case '^' -> position.y++;
            case 'v' -> position.y--;
        }
    }
    
    /// @param inputLines the puzzle input.
    /// @return the number of houses that received at least one present.
    @Override
    public Object partTwo(List<String> inputLines) {
        String inputLine = inputLines.getFirst();
        
        Point currentPositionSanta = new Point();
        Point currentPositionRobot = new Point();
        
        HashSet<String> previousPositions = new HashSet<>();
        
        previousPositions.add(currentPositionSanta.toString());
        int uniqueHousesVisited = 1;
        
        for (int index = 0; index < inputLine.length(); index++) {
            if (isEven(index)) {
                updatePosition(currentPositionSanta, inputLine.charAt(index));
                
                if (!previousPositions.contains(currentPositionSanta.toString())) {
                    previousPositions.add(currentPositionSanta.toString());
                    uniqueHousesVisited++;
                }
            } else {
                updatePosition(currentPositionRobot, inputLine.charAt(index));
                
                if (!previousPositions.contains(currentPositionRobot.toString())) {
                    previousPositions.add(currentPositionRobot.toString());
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
}
