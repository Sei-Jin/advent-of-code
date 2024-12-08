package aoc.event.year2022.day04.campCleanup;

import aoc.PuzzleSolver;

import java.util.List;

public class Solution implements PuzzleSolver
{
    /// Calculates the number of instances where one of the two ranges fully contains another.
    ///
    /// The puzzle input contains two ranges on each line, such as `2-4,4-6`. Here the first range would
    /// be `2-4` and the second range would be `4-6`.
    ///
    /// - Time Complexity: O(n)
    ///     - A single pass is done over the puzzle input.
    ///
    /// - Space Complexity: O(1)
    ///     - One pair of ranges is stored at a time for each line of the puzzle input.
    ///
    /// @param inputLines the puzzle input.
    /// @return  the number of instances where one of the two ranges fully contains another.
    @Override
    public Object partOne(List<String> inputLines)
    {
        int fullyContainedRanges = 0;
        
        for (String line : inputLines)
        {
            RangePair ranges = getRanges(line);
            
            if (isFullyContainedRange(ranges))
            {
                fullyContainedRanges++;
            }
        }
        
        return fullyContainedRanges;
    }
    
    /// These records store the parsed data for a single line of the puzzle input.
    record Range(int startingSection, int endingSection) {}
    record RangePair(Range firstRange, Range secondRange) {}
    
    /// Parses a line of the puzzle input to create a pair of ranges.
    ///
    /// The puzzle input is in the form `A-B,C-D`, where `A-B` is the first range and `C-D` is the second range.
    ///
    /// @param line a line of the puzzle input.
    /// @return the pair of ranges.
    private static RangePair getRanges(String line)
    {
        String[] rangeInput = line.split(",");
        
        Range firstRange = parseRange(rangeInput[0]);
        Range secondRange = parseRange(rangeInput[1]);
        
        return new RangePair(firstRange, secondRange);
    }
    
    /// Parses a part of the puzzle input for the range values to create a new range.
    ///
    /// The part of the puzzle input is in the form `A-B`, where A is the start of the range and B is the end
    /// of the range.
    ///
    /// @param rangeInput a part of the puzzle input that represents a range.
    /// @return a new range with the parsed values.
    private static Range parseRange(String rangeInput)
    {
        String[] endpointsInput = rangeInput.split("-");
        
        int startingSection = Integer.parseInt(endpointsInput[0]);
        int endingSection =  Integer.parseInt(endpointsInput[1]);
        
        return new Range(startingSection, endingSection);
    }
    
    /// Returns true if one of the two ranges are fully contained by the other, and false otherwise.
    ///
    /// @param ranges a list of ranges.
    /// @return true if one of the two ranges are fully contained by the other, and false otherwise.
    private static boolean isFullyContainedRange(RangePair ranges)
    {
        Range firstRange = ranges.firstRange;
        Range secondRange = ranges.secondRange;
        
        boolean firstContainsSecond = (
                firstRange.startingSection <= secondRange.startingSection &&
                firstRange.endingSection >= secondRange.endingSection
        );
        
        boolean secondContainsFirst = (
                secondRange.startingSection <= firstRange.startingSection &&
                secondRange.endingSection >= firstRange.endingSection
        );
        
        return (firstContainsSecond || secondContainsFirst);
    }
    
    /// Calculates the number of instances where one of the two ranges fully contains another.
    ///
    /// - Time Complexity: O(n)
    ///     - A single pass is done over the puzzle input.
    ///
    /// - Space Complexity: O(1)
    ///     - One pair of ranges is stored at a time for each line of the puzzle input.
    ///
    /// @param inputLines the puzzle input.
    /// @return  the number of instances where one of the two ranges fully contains another.
    @Override
    public Object partTwo(List<String> inputLines)
    {
        int overlappingRanges = 0;
        
        for (String line : inputLines)
        {
            RangePair ranges = getRanges(line);
            
            if (isOverlappingRange(ranges))
            {
                overlappingRanges++;
            }
        }
        
        return overlappingRanges;
    }
    
    /// Returns true if the ranges overlap, and false otherwise.
    ///
    /// The ranges are overlapping if one of them starts before the other ends.
    ///
    /// @param ranges a pair of two ranges.
    /// @return true if the ranges overlap, false if otherwise.
    private static boolean isOverlappingRange(RangePair ranges)
    {
        Range firstRange = ranges.firstRange;
        Range secondRange = ranges.secondRange;
        
        return (firstRange.startingSection <= secondRange.endingSection &&
                secondRange.startingSection <= firstRange.endingSection);
    }
}
