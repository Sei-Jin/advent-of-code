package aoc.event.year2015.day05.doesntHeHaveInternElvesForThis;

import aoc.Runner;
import aoc.Solver;

import java.util.List;

public class Solution implements Solver {
    
    private final List<String> lines;
    
    public Solution(String input) {
        lines = input.lines().toList();
    }
    
    /// @return the total number of nice strings.
    @Override
    public Integer partOne() {
        int totalNiceStrings = 0;
        
        for (final var line : lines) {
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
    
    /// @return the total number of nice strings under the new rules.
    @Override
    public Integer partTwo() {
        int totalNiceStrings = 0;
        
        for (final var line : lines) {
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
    
    public static void main(String[] args) {
        Runner.runAndPrint(2015, 5);
    }
}
