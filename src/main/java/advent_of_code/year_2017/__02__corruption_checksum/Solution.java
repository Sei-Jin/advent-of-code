package advent_of_code.year_2017.__02__corruption_checksum;

import advent_of_code.PuzzleSolver;

import java.util.Arrays;
import java.util.List;

/**
 * --- Day 2: Corruption Checksum ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the checksum for the spreadsheet.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        int checkSumDifference = 0;
        
        for (String line : inputLines)
        {
            List<Integer> numbers = getNumbers(line);
            checkSumDifference += findDifference(numbers);
        }
        
        return checkSumDifference;
    }
    
    
    /**
     * @param line a line from the puzzle input.
     * @return a {@code List} of the numbers in {@code line}.
     */
    private static List<Integer> getNumbers(String line)
    {
        return Arrays.stream(line.split("\\s"))
                .map(Integer::parseInt)
                .toList();
    }
    
    
    private static int findDifference(List<Integer> numbers)
    {
        int minimumNumber = numbers.getFirst();
        int maximumNumber = numbers.getFirst();
        
        for (int number : numbers)
        {
            if (minimumNumber > number)
            {
                minimumNumber = number;
            }
            
            if (maximumNumber < number)
            {
                maximumNumber = number;
            }
        }
        
        return maximumNumber - minimumNumber;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the sum of each row's divisible result.
     */
    @Override
    public Object partTwo(List<String> inputLines)
    {
        int checkSumDivisible = 0;
        
        for (String line : inputLines)
        {
            List<Integer> numbers = getNumbers(line);
            checkSumDivisible += findDivisible(numbers);
        }
        
        return checkSumDivisible;
    }
    
    
    private static int findDivisible(List<Integer> numbers)
    {
        for (int outerIndex = 0; outerIndex < numbers.size(); outerIndex++)
        {
            for (int innerIndex = 0; innerIndex < numbers.size(); innerIndex++)
            {
                if (outerIndex == innerIndex)
                {
                    continue;
                }
                
                boolean isDivisible = (numbers.get(outerIndex) % numbers.get(innerIndex) == 0);
                
                if (isDivisible)
                {
                    return numbers.get(outerIndex) / numbers.get(innerIndex);
                }
            }
        }
        
        return 0;
    }
}
