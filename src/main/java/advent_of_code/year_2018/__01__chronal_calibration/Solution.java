package advent_of_code.year_2018.__01__chronal_calibration;

import advent_of_code.PuzzleSolver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * --- Day 1: Chronal Calibration ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the resulting frequency.
     */
    public Object partOne(List<String> inputLines)
    {
        int currentFrequency = 0;
        
        for (String line : inputLines)
        {
            char sign = line.charAt(0);
            int frequencyChange = Integer.parseInt(line.substring(1));
            
            if (sign == '-')
            {
                frequencyChange *= -1;
            }
            
            currentFrequency += frequencyChange;
        }
        
        return currentFrequency;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the first frequency reached twice.
     */
    public Object partTwo(List<String> inputLines)
    {
        int startingFrequency = 0;
        
        return findDuplicateFrequency(inputLines, startingFrequency);
    }
    
    
    private static int findDuplicateFrequency(List<String> inputLines, int startingFrequency)
    {
        Set<Integer> previousFrequencies = new HashSet<>();
        
        previousFrequencies.add(startingFrequency);
        int currentFrequency = startingFrequency;
        
        boolean foundDuplicate = false;
        
        while (!foundDuplicate)
        {
            for (String line : inputLines)
            {
                char sign = line.charAt(0);
                int frequencyChange = Integer.parseInt(line.substring(1));
                
                if (sign == '-')
                {
                    frequencyChange *= -1;
                }
                
                currentFrequency += frequencyChange;
                
                if (previousFrequencies.contains(currentFrequency))
                {
                    foundDuplicate = true;
                    break;
                }
                else
                {
                    previousFrequencies.add(currentFrequency);
                }
            }
        }
        
        return currentFrequency;
    }
}
