package year2017;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * --- Day 2: Corruption Checksum ---
 */
public class Day02
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
        partTwo(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines)
    {
        int checkSumDifference = 0;
        
        for (String line : inputLines)
        {
            List<Integer> numbers = getNumbers(line);
            
            checkSumDifference += findDifference(numbers);
        }
        
        System.out.println("The checksum for the spreadsheet is: " + checkSumDifference);
    }
    
    
    private static void partTwo(List<String> inputLines)
    {
        int checkSumDivisible = 0;
        
        for (String line : inputLines)
        {
            List<Integer> numbers = getNumbers(line);
            
            checkSumDivisible += findDivisible(numbers);
        }
        
        System.out.println("The sum of each row's divisible result is: " + checkSumDivisible);
    }
    
    
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
    
    
    private static int findDivisible(List<Integer> numbers)
    {
        for (int currentIndex = 0; currentIndex < numbers.size(); currentIndex++)
        {
            for (int comparisonIndex = 0; comparisonIndex < numbers.size(); comparisonIndex++)
            {
                if (currentIndex == comparisonIndex)
                {
                    continue;
                }
                
                boolean isDivisible = (numbers.get(currentIndex) % numbers.get(comparisonIndex) == 0);
                
                if (isDivisible)
                {
                    return numbers.get(currentIndex) / numbers.get(comparisonIndex);
                }
            }
        }
        
        return 0;
    }
}
