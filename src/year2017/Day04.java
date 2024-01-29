package year2017;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Day04
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines)
    {
        int validPassphrases = 0;
        
        for (String line : inputLines)
        {
            List<String> words = Arrays.stream(line.split(" "))
                    .toList();
            
            HashSet<String> wordsEncountered = new HashSet<>();
            
            if (!containsDuplicate(words, wordsEncountered))
            {
                validPassphrases++;
            }
        }
        
        System.out.println("The number of valid passphrases is: " + validPassphrases);
    }
    
    
    private static boolean containsDuplicate(List<String> words, HashSet<String> wordsEncountered)
    {
        for (String word : words)
        {
            if (wordsEncountered.contains(word))
            {
                return true;
            }
            else
            {
                wordsEncountered.add(word);
            }
        }
        
        return false;
    }
}
