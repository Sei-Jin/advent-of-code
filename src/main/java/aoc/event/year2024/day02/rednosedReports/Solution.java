package aoc.event.year2024.day02.rednosedReports;

import aoc.PuzzleSolver;

import java.util.Arrays;
import java.util.List;

public class Solution implements PuzzleSolver
{
    @Override
    public Object partOne(List<String> inputLines)
    {
        int safeReports = 0;
        
        for (String line : inputLines)
        {
            String whitespace = "\\s";
            List<Integer> levels = Arrays.stream(line.split(whitespace))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
            
            boolean allDecreasing = true, allIncreasing = true, safeDifference = true;
            
            for (int index = 0; index < levels.size() - 1; index++)
            {
                if (levels.get(index) < levels.get(index + 1))
                {
                    allDecreasing = false;
                }
                else if ((levels.get(index) > levels.get(index + 1)))
                {
                    allIncreasing = false;
                }
                else
                {
                    allIncreasing = false;
                    allDecreasing = false;
                }
                
                int difference = Math.abs(levels.get(index) - levels.get(index + 1));
                
                if (difference < 1 || difference > 3)
                {
                    safeDifference = false;
                }
            }
            
            if ((allDecreasing || allIncreasing) && safeDifference)
            {
                safeReports += 1;
            }
        }
        
        return safeReports;
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
