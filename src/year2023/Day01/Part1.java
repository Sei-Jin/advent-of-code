package year2023.Day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * --- Day 1: Trebuchet?! ---
 */
public class Part1
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("sample.txt"));
        
        partOne(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines)
    {
        int calibrationTotal = 0;
        
        for (String line : inputLines)
        {
            int firstDigit = findFirstDigit(line);
            
            String reversedLine = new StringBuilder(line).reverse().toString();
            
            int lastDigit = findFirstDigit(reversedLine);
            
            int calibrationValue = firstDigit * 10 + lastDigit;
            calibrationTotal += calibrationValue;
        }
        
        System.out.println(calibrationTotal);
    }
    
    
    private static int findFirstDigit(String line)
    {
        int firstDigit = 0;

        for (int i = 0; i < line.length(); i++)
        {
            char character = line.charAt(i);
            
            if (Character.isDigit(character))
            {
                firstDigit = Character.getNumericValue(character);
                break;
            }
        }
        
        return firstDigit;
    }
}