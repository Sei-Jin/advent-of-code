package advent_of_code.year_2015.__05__doesnt_he_have_intern_elves_for_this;

import advent_of_code.PuzzleSolver;

import java.util.List;

/**
 * --- Day 5: Doesn't He Have Intern-Elves For This? ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input
     * @return the total number of nice strings.
     */
    public Object partOne(List<String> inputLines)
    {
        int totalNiceStrings = 0;
        
        for (String line : inputLines)
        {
            boolean niceString = true;
            
            // The line must contain at least 3 vowels (aeiou only)
            if (!line.matches("^([^aeiou]*[aeiou]){3,}[^aeiou]*$"))
            {
                niceString = false;
            }
            
            // The line must contain at least one letter that appears twice in a row
            if (!line.matches("^.*([a-z])\\1.*$"))
            {
                niceString = false;
            }
            
            // The line cannot contain the strings "ab", "cd", "pq", and "xy"
            if (!line.matches("^(?:(?!ab|cd|pq|xy).)*$"))
            {
                niceString = false;
            }
            
            if (niceString)
            {
                totalNiceStrings++;
            }
        }
        
        return totalNiceStrings;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the total number of nice strings under the new rules.
     */
    public Object partTwo(List<String> inputLines)
    {
        int totalNiceStrings = 0;
        
        for (String line : inputLines)
        {
            boolean niceString = true;
            
            // The line contains a pair of any two letters that appears at least twice in the string without overlapping
            if (!line.matches("^.*([a-z]{2}).*\\1.*$"))
            {
                niceString = false;
            }
            
            // The line contains at least one letter that repeats with exactly 1 letter between them
            if (!line.matches("^.*([a-z]).\\1.*$"))
            {
                niceString = false;
            }
            
            if (niceString)
            {
                totalNiceStrings++;
            }
        }
        
        return totalNiceStrings;
    }
}
