package aoc.event.year2022;

import aoc.Solver;

import java.util.HashMap;

/// # [2022-06: Tuning Trouble](https://adventofcode.com/2022/day/6)
public class Day06 implements Solver<Integer, Integer> {
    
    private final String string;
    
    public Day06(String input) {
        this.string = input;
    }
    
    /// Calculates the number of characters that need to be processed before there is a unique
    /// sequence of characters with length `windowSize`.
    ///
    /// This method runs in linear time.
    ///
    /// @param windowSize the window size of distinct characters.
    /// @return the position of the first marker.
    private static int findFirstMarker(String string, int windowSize) {
        if (string.length() < windowSize) {
            throw new IllegalArgumentException("String was less than the window size");
        }
        var counts = new HashMap<Character, Integer>();
        var left = 0;
        
        for (var right = 0; right < string.length(); right++) {
            var rightCharacter = string.charAt(right);
            var rightCount = counts.getOrDefault(rightCharacter, 0) + 1;
            counts.put(rightCharacter, rightCount);
            
            if (right - left + 1 == windowSize) {
                if (counts.size() == windowSize) {
                    return right + 1;
                }
                var leftCharacter = string.charAt(left);
                var leftCount = counts.get(leftCharacter) - 1;
                counts.put(leftCharacter, leftCount);
                
                if (leftCount == 0) {
                    counts.remove(leftCharacter);
                }
                left++;
            }
        }
        throw new IllegalArgumentException("No markers were detected.");
    }
    
    @Override
    public Integer partOne() {
        return findFirstMarker(string, 4);
    }
    
    @Override
    public Integer partTwo() {
        return findFirstMarker(string, 14);
    }
}
