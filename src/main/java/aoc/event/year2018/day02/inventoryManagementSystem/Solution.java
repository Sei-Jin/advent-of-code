package aoc.event.year2018.day02.inventoryManagementSystem;

import aoc.Runner;
import aoc.Solver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution implements Solver {
    
    private final List<String> lines;
    
    public Solution(String input) {
        lines = input.lines().collect(Collectors.toUnmodifiableList());
    }
    
    /// Calculates the checksum for the list of box ids.
    ///
    /// To calculate the checksum, multiply the number of box ids that contain exactly two of any
    /// character with the number of box ids that contain exactly three of any character.
    ///
    /// Time Complexity: `O(n)`
    ///
    /// Space Complexity: `O(n)`
    ///
    /// @return the checksum for the list of box IDs.
    @Override
    public Integer partOne() {
        int twoCount = 0;
        int threeCount = 0;
        
        for (final var line : lines) {
            Map<Character, Integer> characterCount = calculateCharacterCount(line);
            
            if (containsCopies(characterCount, 2)) {
                twoCount++;
            }
            
            if (containsCopies(characterCount, 3)) {
                threeCount++;
            }
        }
        
        return twoCount * threeCount;
    }
    
    private static Map<Character, Integer> calculateCharacterCount(String line) {
        Map<Character, Integer> letterCount = new HashMap<>();
        
        for (int i = 0; i < line.length(); i++) {
            final var character = line.charAt(i);
            int count = letterCount.getOrDefault(character, 0) + 1;
            letterCount.put(character, count);
        }
        
        return letterCount;
    }
    
    private static boolean containsCopies(Map<Character, Integer> letterCount, int copies) {
        for (int count : letterCount.values()) {
            if (count == copies) {
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
    /// @return the characters common between the two correct box IDs.
    @Override
    public String partTwo() {
        for (int outerIndex = 0; outerIndex < lines.size(); outerIndex++) {
            for (int innerIndex = 0; innerIndex < lines.size(); innerIndex++) {
                if (outerIndex == innerIndex) {
                    continue;
                }
                
                String mainLine = lines.get(outerIndex);
                String comparisonLine = lines.get(innerIndex);
                
                int differentCharacters = 0;
                
                for (int charIndex = 0; charIndex < mainLine.length(); charIndex++) {
                    if (mainLine.charAt(charIndex) != comparisonLine.charAt(charIndex)) {
                        differentCharacters++;
                    }
                }
                
                if (differentCharacters == 1) {
                    int removeIndex = 0;
                    
                    for (int charIndex = 0; charIndex < mainLine.length(); charIndex++) {
                        if (mainLine.charAt(charIndex) != comparisonLine.charAt(charIndex)) {
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
    
    public static void main(String[] args) {
        Runner.runAndPrint(2018, 2);
    }
}
