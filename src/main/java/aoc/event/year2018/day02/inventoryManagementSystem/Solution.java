package aoc.event.year2018.day02.inventoryManagementSystem;

import aoc.DeprecatedSolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution implements DeprecatedSolver
{
    /// Calculates the checksum for the list of box ids.
    ///
    /// To calculate the checksum, multiply the number of box ids that contain exactly two of any
    /// character with the number of box ids that contain exactly three of any character.
    ///
    /// Time Complexity: `O(n)`
    ///
    /// Space Complexity: `O(n)`
    ///
    /// @param puzzleInput the puzzle input.
    /// @return the checksum for the list of box IDs.
    @Override
    public Object partOne(List<String> puzzleInput)
    {
        int twoCount = 0;
        int threeCount = 0;
        
        for (String line : puzzleInput)
        {
            List<Character> characters = line.chars()
                    .mapToObj(character -> (char) character)
                    .toList();
            
            Map<Character, Integer> characterCount = calculateCharacterCount(characters);
            
            if (containsCopies(characterCount, 2))
            {
                twoCount++;
            }
            
            if (containsCopies(characterCount, 3))
            {
                threeCount++;
            }
        }
        
        return twoCount * threeCount;
    }
    
    private static Map<Character, Integer> calculateCharacterCount(List<Character> characters)
    {
        Map<Character, Integer> letterCount = new HashMap<>();
        
        for (char character : characters)
        {
            int count = letterCount.getOrDefault(character, 0) + 1;
            letterCount.put(character, count);
        }
        
        return letterCount;
    }
    
    private static boolean containsCopies(Map<Character, Integer> letterCount, int copies)
    {
        for (int count : letterCount.values())
        {
            if (count == copies)
            {
                return true;
            }
        }
        
        return false;
    }
    
    /// Creates a new string using the common characters of the two correct box ids.
    ///
    /// The two correct box ids are the box ids that differ by exactly one character.
    ///
    /// Time Complexity: `O(n^2)`
    ///
    /// Space Complexity: `O(n)`
    ///
    /// @param puzzleInput the puzzle input.
    /// @return the characters common between the two correct box IDs.
    @Override
    public Object partTwo(List<String> puzzleInput)
    {
        for (int outerIndex = 0; outerIndex < puzzleInput.size(); outerIndex++)
        {
            for (int innerIndex = 0; innerIndex < puzzleInput.size(); innerIndex++)
            {
                if (outerIndex == innerIndex)
                {
                    continue;
                }
                
                String mainLine = puzzleInput.get(outerIndex);
                String comparisonLine = puzzleInput.get(innerIndex);
                
                int differentCharacters = 0;
                
                for (int charIndex = 0; charIndex < mainLine.length(); charIndex++)
                {
                    if (mainLine.charAt(charIndex) != comparisonLine.charAt(charIndex))
                    {
                        differentCharacters++;
                    }
                }
                
                if (differentCharacters == 1)
                {
                    int removeIndex = 0;
                    
                    for (int charIndex = 0; charIndex < mainLine.length(); charIndex++)
                    {
                        if (mainLine.charAt(charIndex) != comparisonLine.charAt(charIndex))
                        {
                            removeIndex = charIndex;
                        }
                    }
                    
                    return mainLine.substring(0, removeIndex) +
                            mainLine.substring(removeIndex + 1);
                }
            }
        }
        
        throw new IllegalStateException("Error: No box IDs differed by exactly one character.");
    }
}
