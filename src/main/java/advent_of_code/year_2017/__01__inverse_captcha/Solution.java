package advent_of_code.year_2017.__01__inverse_captcha;

import advent_of_code.PuzzleSolver;

import java.util.List;

/**
 * --- Day 1: Inverse Captcha ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the solution to the captcha.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        String inputLine = inputLines.getFirst();
        
        int offset = 1;
        
        return solveCaptcha(inputLine, offset);
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the solution to the new captcha.
     */
    @Override
    public Object partTwo(List<String> inputLines)
    {
        String inputLine = inputLines.getFirst();
        
        int offset = inputLine.length() / 2;
        
        return solveCaptcha(inputLine, offset);
    }
    
    
    private static int solveCaptcha(String line, int offset)
    {
        int runningTotal = 0;
        
        for (int index = 0; index < line.length(); index++)
        {
            if (line.charAt(index) == line.charAt((index + offset) % line.length()))
            {
                runningTotal += Character.getNumericValue(line.charAt(index));
            }
        }
        
        return runningTotal;
    }
}
