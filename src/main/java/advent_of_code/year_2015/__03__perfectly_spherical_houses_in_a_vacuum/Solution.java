package advent_of_code.year_2015.__03__perfectly_spherical_houses_in_a_vacuum;

import advent_of_code.PuzzleSolver;

import java.awt.*;
import java.util.HashSet;
import java.util.List;

/**
 * --- Day 3: Perfectly Spherical Houses in a Vacuum ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the number of houses that received at least one present.
     */
    public Object partOne(List<String> inputLines)
    {
        String inputLine = inputLines.getFirst();
        
        Point currentPosition = new Point();
        
        HashSet<String> previousPositions = new HashSet<>();
        
        previousPositions.add(currentPosition.toString());
        int uniqueHousesVisited = 1;
        
        for (int i = 0; i < inputLine.length(); i++)
        {
            updatePosition(currentPosition, inputLine.charAt(i));
            
            if (newHouseVisited(previousPositions, currentPosition))
            {
                uniqueHousesVisited++;
            }
        }

        return uniqueHousesVisited;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the number of houses that received at least one present.
     */
    public Object partTwo(List<String> inputLines)
    {
        String inputLine = inputLines.getFirst();
        
        Point currentPositionSanta = new Point();
        Point currentPositionRobot = new Point();
        
        HashSet<String> previousPositions = new HashSet<>();
        
        previousPositions.add(currentPositionSanta.toString());
        int uniqueHousesVisited = 1;
        
        for (int i = 0; i < inputLine.length(); i++)
        {
            if (i % 2 == 0)
            {
                updatePosition(currentPositionSanta, inputLine.charAt(i));
                
                if (newHouseVisited(previousPositions, currentPositionSanta))
                {
                    uniqueHousesVisited++;
                }
            }
            else
            {
                updatePosition(currentPositionRobot, inputLine.charAt(i));
                
                if (newHouseVisited(previousPositions, currentPositionRobot))
                {
                    uniqueHousesVisited++;
                }
            }
        }
        
        return uniqueHousesVisited;
    }
    
    
    private static boolean newHouseVisited(HashSet<String> previousPositions, Point currentPositionRobot)
    {
        if (!previousPositions.contains(currentPositionRobot.toString()))
        {
            previousPositions.add(currentPositionRobot.toString());
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    private static void updatePosition(Point position, char direction)
    {
        switch (direction)
        {
            case '>' -> position.x++;
            case '<' -> position.x--;
            case '^' -> position.y++;
            case 'v' -> position.y--;
        }
    }
}
