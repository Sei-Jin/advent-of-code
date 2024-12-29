package aoc.event.year2016.day06.signalsAndNoise;

import aoc.PuzzleSolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution implements PuzzleSolver
{
    private static Map<Character, Integer> getCharacterCounts(
            List<String> inputLines,
            int index)
    {
        Map<Character, Integer> characterCounts = new HashMap<>();
        
        for (String line : inputLines)
        {
            char character = line.charAt(index);
            int count = characterCounts.getOrDefault(character, 0) + 1;
            
            characterCounts.put(character, count);
        }
        
        return characterCounts;
    }
    
    @Override
    public Object partOne(List<String> inputLines)
    {
        StringBuilder messageBuilder = new StringBuilder();
        
        for (int index = 0; index < inputLines.getFirst().length(); index++)
        {
            Map<Character, Integer> characterCounts = getCharacterCounts(inputLines, index);
            
            char mostFrequentCharacter = getMostFrequentCharacter(characterCounts);
            messageBuilder.append(mostFrequentCharacter);
        }
        
        return messageBuilder.toString();
    }
    
    private static char getMostFrequentCharacter(Map<Character, Integer> characterCounts)
    {
        char mostFrequentCharacter = 0;
        int maxCount = 0;
        
        for (char character : characterCounts.keySet())
        {
            int count = characterCounts.get(character);
            
            if (count > maxCount)
            {
                maxCount = count;
                mostFrequentCharacter = character;
            }
        }
        
        return mostFrequentCharacter;
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
