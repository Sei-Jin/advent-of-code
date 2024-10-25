package advent_of_code.year2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

/**
 * --- Day 1: Trebuchet?! ---
 */
public class Day01
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
        partTwo(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines)
    {
        int calibrationTotal = 0;
        
        for (String line : inputLines)
        {
            int firstDigit = 0, lastDigit = 0;
            
            // Scan over the string looking for the first digit
            for (int i = 0; i < line.length(); i++)
            {
                if (Character.isDigit(line.charAt(i)))
                {
                    firstDigit = Character.getNumericValue(line.charAt(i));
                    break;
                }
            }
            
            // Scan over the string looking for the last digit
            for (int i = line.length() - 1; i >= 0; i--)
            {
                if (Character.isDigit(line.charAt(i)))
                {
                    lastDigit = Character.getNumericValue(line.charAt(i));
                    break;
                }
            }
            
            int calibrationValue = firstDigit * 10 + lastDigit;
            calibrationTotal += calibrationValue;
        }
        
        System.out.println("The sum of all the calibration values is: " + calibrationTotal);
    }
    
    
    private static void partTwo(List<String> inputLines)
    {
        int calibrationTotal = 0;
        
        HashMap<String, Integer> digitsMap = getDigitsMap();
        
        for (String line : inputLines)
        {
            int firstDigit = 0, lastDigit = 0;
            
            // Scan over the string looking for the first digit
            for (int i = 0; i < line.length(); i++)
            {
                if (Character.isDigit(line.charAt(i)))
                {
                    firstDigit = Character.getNumericValue(line.charAt(i));
                    break;
                }
                
                // From the current index, compare the next 3, 4, and 5 length strings to the digits map
                for (int j = 3; j <= 5; j++)
                {
                    StringBuilder letters = new StringBuilder();
                    
                    for (int k = i; k < line.length() && k < i + j; k++)
                    {
                        letters.append(line.charAt(k));
                    }
                    
                    if (digitsMap.containsKey(letters.toString()))
                    {
                        firstDigit = digitsMap.get(letters.toString());
                    }
                }
                
                // Break out of the loop if we found a matching digit
                if (firstDigit != 0)
                {
                    break;
                }
            }
            
            // Scan over the string looking for the last digit
            for (int i = line.length() - 1; i >= 0; i--)
            {
                if (Character.isDigit(line.charAt(i)))
                {
                    lastDigit = Character.getNumericValue(line.charAt(i));
                    break;
                }
                
                // From the current index, compare the next 3, 4, and 5 length strings to the digits map
                for (int j = 3; j <= 5; j++)
                {
                    StringBuilder letters = new StringBuilder();
                    
                    for (int k = i - j + 1; k >= 0 && k <= i; k++)
                    {
                        letters.append(line.charAt(k));
                    }
                    
                    if (digitsMap.containsKey(letters.toString()))
                    {
                        lastDigit = digitsMap.get(letters.toString());
                    }
                }
                
                // Break out of the loop if we found a matching digit
                if (lastDigit != 0)
                {
                    break;
                }
            }
            
            int calibrationValue = firstDigit * 10 + lastDigit;
            calibrationTotal += calibrationValue;
        }
        
        System.out.println("The sum of all the calibration values is: " + calibrationTotal);
    }
    
    
    private static HashMap<String, Integer> getDigitsMap()
    {
        HashMap<String, Integer> digitsMap = new HashMap<>();
        
        digitsMap.put("one", 1);
        digitsMap.put("two", 2);
        digitsMap.put("three", 3);
        digitsMap.put("four", 4);
        digitsMap.put("five", 5);
        digitsMap.put("six", 6);
        digitsMap.put("seven", 7);
        digitsMap.put("eight", 8);
        digitsMap.put("nine", 9);
        
        return digitsMap;
    }
}