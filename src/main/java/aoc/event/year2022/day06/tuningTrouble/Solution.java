package aoc.event.year2022.day06.tuningTrouble;

import aoc.Solver;
import aoc.Runner;

public class Solution implements Solver {
    
    private final String buffer;
    
    public Solution(String input) {
        this.buffer = input;
    }
    
    /// Finds the position of the first marker.
    ///
    /// The position of the first marker is calculated by finding the first substring of distinct
    /// characters with length `windowSize`.
    ///
    /// @param line       finds the position of the first marker.
    /// @param windowSize the length of distinct characters.
    /// @return the position of the first marker.
    private static int findFirstMarker(String line, int windowSize) {
        for (var i = 0; i < line.length(); i++) {
            final var substring = line.substring(i, i + windowSize);
            final var containsDuplicate = containsDuplicate(substring);
            
            if (!containsDuplicate) {
                return i + windowSize;
            }
        }
        
        throw new IllegalStateException("No markers were detected.");
    }
    
    /// Determines if the string contains a duplicate character.
    ///
    /// @param string a string.
    /// @return true if the string contains a duplicate character, or false otherwise.
    private static boolean containsDuplicate(String string) {
        for (var i = 0; i < string.length() - 1; i++) {
            for (var j = i + 1; j < string.length(); j++) {
                if (string.charAt(i) == string.charAt(j)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /// Calculates the position of the first start-of-packet marker.
    ///
    /// The start-of-packet marker starts after the first substring of length 4 with all distinct
    /// characters.
    ///
    /// @return the position of the first start-of-packet marker.
    @Override
    public Object partOne() {
        return findFirstMarker(buffer, 4);
    }
    
    /// Calculates the position of the first start-of-message marker.
    ///
    /// The start-of-message marker starts after the first substring of length 14 with all distinct
    /// characters.
    ///
    /// @return the position of the first start-of-message marker.
    @Override
    public Object partTwo() {
        return findFirstMarker(buffer, 14);
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2022, 6);
    }
}
