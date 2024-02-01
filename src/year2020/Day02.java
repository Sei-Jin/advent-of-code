package year2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
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
    }
    
    
    private static void partOne(List<String> inputLines)
    {
        int validPasswords = 0;
        
        HashMap<Character, Integer> letterCount = new HashMap<>();
        
        for (String line : inputLines)
        {
            List<String> lineValues = Arrays.stream(line.split("(-| |: )"))
                    .toList();
            
            int minimumCount = Integer.parseInt(lineValues.get(0));
            int maximumCount = Integer.parseInt(lineValues.get(1));
            char policyLetter = lineValues.get(2).charAt(0);
            String password = lineValues.get(3);
            
            letterCount.put(policyLetter, 0);
            
            for (char character : password.toCharArray())
            {
                if (character == policyLetter)
                {
                    letterCount.merge(policyLetter, 1, Integer::sum);
                }
            }
            
            int count = letterCount.get(policyLetter);
            
            if (count >= minimumCount && count <= maximumCount)
            {
                validPasswords++;
            }
        }
        
        System.out.println("The number of valid passwords according to the given policies is: " + validPasswords);
    }
}
