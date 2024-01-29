package year2017;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * --- Day 4: High-Entropy Passphrases ---
 */
public class Day04
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
        partTwo(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines)
    {
        int validPassphrases = 0;
        
        for (String line : inputLines)
        {
            List<String> wordList = getWordList(line);
            
            if (!containsDuplicate(wordList)) {
                validPassphrases++;
            }
        }
        
        System.out.println("The number of valid passphrases is: " + validPassphrases);
    }
    
    
    private static void partTwo(List<String> inputLines)
    {
        int validPassphrases = 0;
        
        for (String line : inputLines)
        {
            List<String> wordList = getWordList(line);
            
            if (!containsAnagram(wordList)) {
                validPassphrases++;
            }
        }
        
        System.out.println("Under the new system policy, the number of valid passphrases is: " + validPassphrases);
    }
    
    
    private static List<String> getWordList(String line)
    {
        return Arrays.stream(line.split(" "))
                .toList();
    }
    
    
    private static boolean containsDuplicate(List<String> words)
    {
        HashSet<String> wordsEncountered = new HashSet<>();
        
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
    
    
    private static boolean containsAnagram(List<String> wordList)
    {
        HashSet<List<Character>> Anagrams = new HashSet<>();
        
        for (String word : wordList)
        {
            List<Character> sortedWord = word.chars()
                    .mapToObj(c -> (char) c)
                    .sorted()
                    .toList();
            
            if (Anagrams.contains(sortedWord))
            {
                return true;
            }
            else
            {
                Anagrams.add(sortedWord);
            }
        }
        
        return false;
    }
}
