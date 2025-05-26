package aoc.event.year2018;

import aoc.DeprecatedSolver2;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day01 implements DeprecatedSolver2 {
    
    /// The starting frequency value.
    private static final int STARTING_FREQUENCY = 0;
    
    private final List<Integer> frequencies;
    
    public Day01(String input) {
        frequencies = Collections.unmodifiableList(parse(input));
    }
    
    /// Parses a frequency change from the input line.
    ///
    /// The input line is in the form:
    ///
    /// ```
    /// -32
    /// +54
    /// -3
    /// +6
    ///```
    ///
    /// Each line starts with a sign, (+/-), followed by a number.
    /// - The sign represents if the frequency change is positive or negative.
    /// - The number represents the value of the frequency change.
    ///
    /// @param input a line of the puzzle input.
    /// @return the change in frequency.
    private static List<Integer> parse(String input) {
        return input.lines().map(line -> {
            final var sign = line.charAt(0);
            var frequencyChange = Integer.parseInt(line.substring(1));
            
            if (sign == '-') {
                frequencyChange *= -1;
            }
            
            return frequencyChange;
        }).toList();
    }
    
    /// Calculates the resulting frequency after all frequency changes are applied.
    ///
    /// Time Complexity: `O(n)`
    /// - `n` is the number of frequency changes.
    ///
    /// Space Complexity: `O(1)`
    /// - One frequency change is stored at a time.
    ///
    /// @return the resulting frequency.
    @Override
    public Integer partOne() {
        var currentFrequency = STARTING_FREQUENCY;
        
        for (final var frequencyChange : frequencies) {
            currentFrequency += frequencyChange;
        }
        
        return currentFrequency;
    }
    
    /// Calculates the first frequency reached twice.
    ///
    /// If no duplicate frequency is found then the changes will be applied again until a
    /// duplicate frequency is found.
    ///
    /// Time Complexity: `O(n)`
    /// - `n` is the number of frequency changes.
    ///
    /// Space Complexity: `O(n)`
    /// - `n` is the number of frequency changes.
    ///
    /// @return the first frequency reached twice.
    /// @throws IllegalStateException if there is not a frequency reached twice.
    @Override
    public Integer partTwo() {
        int currentFrequency = STARTING_FREQUENCY;
        
        Set<Integer> previousFrequencies = new HashSet<>();
        previousFrequencies.add(STARTING_FREQUENCY);
        
        while (true) {
            for (int frequencyChange : frequencies) {
                currentFrequency += frequencyChange;
                
                if (!previousFrequencies.contains(currentFrequency)) {
                    previousFrequencies.add(currentFrequency);
                } else {
                    return currentFrequency;
                }
            }
        }
    }
}
