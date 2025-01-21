package aoc.event.year2018.day01.chronalCalibration;

import aoc.PuzzleSolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution implements PuzzleSolver
{
    /// The starting frequency value.
    private static final int STARTING_FREQUENCY = 0;
    
    /// Parses a frequency change from the input line.
    ///
    /// The input line is in the form:
    ///
    /// ```
    /// -32
    /// +54
    /// -3
    /// +6
    /// ```
    ///
    /// Each line starts with a sign, (+/-), followed by a number.
    /// - The sign represents if the frequency change is positive or negative.
    /// - The number represents the value of the frequency change.
    ///
    /// @param line a line of the puzzle input.
    /// @return the change in frequency.
    private static int parseFrequencyChange(String line)
    {
        char sign = line.charAt(0);
        int frequencyChange = Integer.parseInt(line.substring(1));
        
        if (sign == '-')
        {
            frequencyChange *= -1;
        }
        
        return frequencyChange;
    }
    
    /// Calculates the resulting frequency after all frequency changes are applied.
    ///
    /// Time Complexity: `O(n)`
    /// - `n` is the number of frequency changes.
    ///
    /// Space Complexity: `O(1)`
    /// - One frequency change is stored at a time.
    ///
    /// @param puzzleInput the puzzle input.
    /// @return the resulting frequency.
    @Override
    public Object partOne(List<String> puzzleInput)
    {
        int currentFrequency = STARTING_FREQUENCY;
        
        for (String line : puzzleInput)
        {
            int frequencyChange = parseFrequencyChange(line);
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
    /// @param puzzleInput the puzzle input.
    /// @return the first frequency reached twice.
    /// @throws IllegalStateException if there is not a frequency reached twice.
    @Override
    public Object partTwo(List<String> puzzleInput)
    {
        List<Integer> frequencyChanges = new ArrayList<>();
        
        for (String line : puzzleInput)
        {
            int frequencyChange = parseFrequencyChange(line);
            frequencyChanges.add(frequencyChange);
        }
        
        int currentFrequency = STARTING_FREQUENCY;
        
        Set<Integer> previousFrequencies = new HashSet<>();
        previousFrequencies.add(STARTING_FREQUENCY);
        
        while (true)
        {
            for (int frequencyChange : frequencyChanges)
            {
                currentFrequency += frequencyChange;
                
                if (!previousFrequencies.contains(currentFrequency))
                {
                    previousFrequencies.add(currentFrequency);
                }
                else
                {
                    return currentFrequency;
                }
            }
        }
    }
}
