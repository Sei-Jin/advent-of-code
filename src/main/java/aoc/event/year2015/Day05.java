package aoc.event.year2015;

import aoc.DeprecatedSolver2;

import java.util.List;

public class Day05 implements DeprecatedSolver2 {
    
    private static final String AT_LEAST_THREE_VOWELS = "^([^aeiou]*[aeiou]){3,}[^aeiou]*$";
    private static final String AT_LEAST_ONE_LETTER_TWICE_IN_A_ROW = "^.*([a-z])\\1.*$";
    private static final String INVALID_SEQUENCES = "^(?:(?!ab|cd|pq|xy).)*$";
    private static final String DUPLICATE_PAIRS = "^.*([a-z]{2}).*\\1.*$";
    private static final String AT_LEAST_ONE_LETTER_REPEATING_WITH_ONE_LETTER_INBETWEEN =
        "^.*([a-z]).\\1.*$";
    
    private final List<String> lines;
    
    public Day05(String input) {
        lines = input.lines().toList();
    }
    
    /// @return the total number of nice strings.
    @Override
    public Integer partOne() {
        var totalNiceStrings = 0;
        
        for (final var line : lines) {
            var niceString = line.matches(AT_LEAST_THREE_VOWELS);
            
            if (!line.matches(AT_LEAST_ONE_LETTER_TWICE_IN_A_ROW)) {
                niceString = false;
            }
            
            if (!line.matches(INVALID_SEQUENCES)) {
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
        var totalNiceStrings = 0;
        
        for (final var line : lines) {
            var niceString = line.matches(DUPLICATE_PAIRS);
            
            if (!line.matches(AT_LEAST_ONE_LETTER_REPEATING_WITH_ONE_LETTER_INBETWEEN)) {
                niceString = false;
            }
            
            if (niceString) {
                totalNiceStrings++;
            }
        }
        
        return totalNiceStrings;
    }
}
