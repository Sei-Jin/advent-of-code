package advent_of_code.year_2015.__01__not_quite_lisp;

import advent_of_code.PuzzleSolver;

import java.util.List;

/**
 * --- Day 1: Not Quite Lisp ---
 */
public class Day01 implements PuzzleSolver
{
    public Integer partOne(List<String> inputLines)
    {
        String inputLine = inputLines.getFirst();
        
        int floorLevel = 0;
        
        for (int i = 0; i < inputLine.length(); i++)
        {
            switch (inputLine.charAt(i))
            {
                case '(' -> floorLevel++;
                case ')' -> floorLevel--;
            }
        }
        
        return floorLevel;
    }
    
    
    public Integer partTwo(List<String> inputLines)
    {
        String inputLine = inputLines.getFirst();
        
        int floorLevel = 0;
        
        for (int instructionNumber = 0; instructionNumber < inputLine.length(); instructionNumber++)
        {
            switch (inputLine.charAt(instructionNumber))
            {
                case '(' -> floorLevel++;
                case ')' -> floorLevel--;
            }
            
            if (floorLevel < 0)
            {
                return instructionNumber + 1;
            }
        }
        
        // Should never reach here
        return -1;
    }
}
