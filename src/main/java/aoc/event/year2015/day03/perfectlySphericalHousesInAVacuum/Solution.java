package aoc.event.year2015.day03.perfectlySphericalHousesInAVacuum;

import aoc.Runner;
import aoc.Solver;

import java.awt.*;
import java.util.HashSet;

public class Solution implements Solver {
    
    private final String line;
    
    public Solution(String input) {
        line = input;
    }
    
    /// @return the number of houses that received at least one present.
    @Override
    public Integer partOne() {
        Point currentPosition = new Point();
        
        HashSet<String> previousPositions = new HashSet<>();
        
        previousPositions.add(currentPosition.toString());
        int uniqueHousesVisited = 1;
        
        for (int index = 0; index < line.length(); index++) {
            updatePosition(currentPosition, line.charAt(index));
            
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
    
    /// @return the number of houses that received at least one present.
    @Override
    public Integer partTwo() {
        Point currentPositionSanta = new Point();
        Point currentPositionRobot = new Point();
        
        HashSet<String> previousPositions = new HashSet<>();
        
        previousPositions.add(currentPositionSanta.toString());
        int uniqueHousesVisited = 1;
        
        for (int index = 0; index < line.length(); index++) {
            if (isEven(index)) {
                updatePosition(currentPositionSanta, line.charAt(index));
                
                if (!previousPositions.contains(currentPositionSanta.toString())) {
                    previousPositions.add(currentPositionSanta.toString());
                    uniqueHousesVisited++;
                }
            } else {
                updatePosition(currentPositionRobot, line.charAt(index));
                
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
    
    public static void main(String[] args) {
        Runner.runAndPrint(2015, 3);
    }
}
