package year2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * --- Day 2: Password Philosophy ---
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
        int validPasswords = 0;
        
        for (String line : inputLines)
        {
            List<String> lineValues = getLineValues(line);
            
            int minimumCount = Integer.parseInt(lineValues.get(0));
            int maximumCount = Integer.parseInt(lineValues.get(1));
            char policyLetter = lineValues.get(2).charAt(0);
            String password = lineValues.get(3);
            
            int letterCount = 0;
            
            for (char character : password.toCharArray())
            {
                if (character == policyLetter)
                {
                    letterCount++;
                }
            }
            
            if (letterCount >= minimumCount && letterCount <= maximumCount)
            {
                validPasswords++;
            }
        }
        
        System.out.println("The number of valid passwords according to the policies is: " + validPasswords);
    }
    
    
    private static void partTwo(List<String> inputLines)
    {
        int validPasswords = 0;
        
        for (String line : inputLines)
        {
            List<String> lineValues = getLineValues(line);
            
            boolean oneLetterPresent = isOneLetterPresent(lineValues);
            
            if (oneLetterPresent)
            {
                validPasswords++;
            }
        }
        
        System.out.println("The number of valid passwords according to the new interpretation of the " +
                "policies is: " + validPasswords);
    }
    
    
    private static List<String> getLineValues(String line)
    {
        return Arrays.stream(line.split("(-| |: )"))
                .toList();
    }
    
    
    private static boolean isOneLetterPresent(List<String> lineValues)
    {
        int firstPosition = Integer.parseInt(lineValues.get(0));
        int secondPosition = Integer.parseInt(lineValues.get(1));
        char policyLetter = lineValues.get(2).charAt(0);
        String password = lineValues.get(3);
        
        boolean oneLetterPresent = false;
        
        if (policyLetter == password.charAt(firstPosition - 1))
        {
            oneLetterPresent = !oneLetterPresent;
        }
        
        if (policyLetter == password.charAt(secondPosition - 1))
        {
            oneLetterPresent = !oneLetterPresent;
        }
        
        return oneLetterPresent;
    }
}
