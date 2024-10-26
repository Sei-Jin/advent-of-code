package advent_of_code.year_2017.__04__high_entropy_passphrases;

import advent_of_code.PuzzleSolver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * --- Day 4: High-Entropy Passphrases ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the number of valid passphrases.
     */
    public Object partOne(List<String> inputLines)
    {
        int validPassphrases = 0;
        
        for (String line : inputLines)
        {
            List<String> wordList = getWordList(line);
            
            if (!containsDuplicate(wordList)) {
                validPassphrases++;
            }
        }
        
        return validPassphrases;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the number of valid passphrases under the new system policy.
     */
    public Object partTwo(List<String> inputLines)
    {
        int validPassphrases = 0;
        
        for (String line : inputLines)
        {
            List<String> wordList = getWordList(line);
            
            if (!containsAnagram(wordList)) {
                validPassphrases++;
            }
        }
        
        return validPassphrases;
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
