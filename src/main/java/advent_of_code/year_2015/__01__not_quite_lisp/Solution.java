package advent_of_code.year_2015.__01__not_quite_lisp;

import advent_of_code.PuzzleSolver;

import java.util.List;

/**
 * --- Day 1: Not Quite Lisp ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the floor level the instructions take Santa to.
     */
    @Override
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
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the position of the character that causes Santa to first enter the basement, or -1 if Santa
     * never enters the basement.
     */
    @Override
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
        
        // The basement was never entered.
        return -1;
    }
}
