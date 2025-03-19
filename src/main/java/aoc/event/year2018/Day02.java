package aoc.event.year2018;

import aoc.Solver;

import java.util.*;

public class Day02 implements Solver {
    
    private final List<List<Character>> characterLists;
    
    public Day02(String input) {
        characterLists = Collections.unmodifiableList(parse(input));
    }
    
    private static List<List<Character>> parse(String input) {
        final var characterLists = new ArrayList<List<Character>>();
        
        for (final var line : input.lines().toList()) {
            final var list = new ArrayList<Character>();
            
            for (int i = 0; i < line.length(); i++) {
                list.add(line.charAt(i));
            }
            
            characterLists.add(Collections.unmodifiableList(list));
        }
        
        return characterLists;
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
        var twoCount = 0;
        var threeCount = 0;
        
        for (final var list : characterLists) {
            final var characterCount = calculateCharacterCount(list);
            
            if (characterCount.containsValue(2)) {
                twoCount++;
            }
            
            if (characterCount.containsValue(3)) {
                threeCount++;
            }
        }
        
        return twoCount * threeCount;
    }
    
    private static Map<Character, Integer> calculateCharacterCount(List<Character> list) {
        final var letterCount = new HashMap<Character, Integer>();
        
        for (final var element : list) {
            final var count = letterCount.getOrDefault(element, 0) + 1;
            letterCount.put(element, count);
        }
        
        return letterCount;
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
        for (var i = 0; i < characterLists.size(); i++) {
            for (var j = 0; j < characterLists.size(); j++) {
                if (i == j) {
                    continue;
                }
                
                final var first = characterLists.get(i);
                final var second = characterLists.get(j);
                
                final var differentCharacters = countDifferentCharacters(first, second);
                
                if (differentCharacters == 1) {
                    final var removeIndex = calculateRemoveIndex(first, second);
                    final var commonCharacters = new ArrayList<>(first);
                    commonCharacters.remove(removeIndex);
                    
                    return buildString(commonCharacters);
                }
            }
        }
        
        throw new IllegalStateException("Error: No box IDs differed by exactly one character.");
    }
    
    private static int countDifferentCharacters(List<Character> first, List<Character> second) {
        var differentCharacters = 0;
        
        for (var i = 0; i < first.size(); i++) {
            if (first.get(i) != second.get(i)) {
                differentCharacters++;
            }
        }
        
        return differentCharacters;
    }
    
    private static int calculateRemoveIndex(List<Character> first, List<Character> second) {
        var removeIndex = 0;
        
        for (var i = 0; i < first.size(); i++) {
            if (first.get(i) != second.get(i)) {
                removeIndex = i;
            }
        }
        
        return removeIndex;
    }
    
    private static String buildString(List<Character> list) {
        final var stringBuilder = new StringBuilder();
        
        for (final var character : list) {
            stringBuilder.append(character);
        }
        
        return stringBuilder.toString();
    }
}
