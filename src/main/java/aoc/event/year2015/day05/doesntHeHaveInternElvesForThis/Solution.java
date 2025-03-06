package aoc.event.year2015.day05.doesntHeHaveInternElvesForThis;

import aoc.DeprecatedSolver;

import java.util.List;

public class Solution implements DeprecatedSolver {
    
    /// @param inputLines the puzzle input
    /// @return the total number of nice strings.
    @Override
    public Object partOne(List<String> inputLines) {
        int totalNiceStrings = 0;
        
        for (String line : inputLines) {
            boolean niceString = line.matches("^([^aeiou]*[aeiou]){3,}[^aeiou]*$");
            
            // The line must contain at least 3 vowels (aeiou only)
            
            // The line must contain at least one letter that appears twice in a row
            if (!line.matches("^.*([a-z])\\1.*$")) {
                niceString = false;
            }
            
            // The line cannot contain the strings "ab", "cd", "pq", and "xy"
            if (!line.matches("^(?:(?!ab|cd|pq|xy).)*$")) {
                niceString = false;
            }
            
            if (niceString) {
                totalNiceStrings++;
            }
        }
        
        return totalNiceStrings;
    }
    
    /// @param inputLines the puzzle input.
    /// @return the total number of nice strings under the new rules.
    @Override
    public Object partTwo(List<String> inputLines) {
        int totalNiceStrings = 0;
        
        for (String line : inputLines) {
            boolean niceString = line.matches("^.*([a-z]{2}).*\\1.*$");
            
            // The line contains a pair of any two letters that appears at least twice in the string without overlapping
            
            // The line contains at least one letter that repeats with exactly 1 letter between them
            if (!line.matches("^.*([a-z]).\\1.*$")) {
                niceString = false;
            }
            
            if (niceString) {
                totalNiceStrings++;
            }
        }
        
        return totalNiceStrings;
    }
}
