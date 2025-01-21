package aoc.event.year2022.day04.campCleanup;

import aoc.PuzzleSolver;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution implements PuzzleSolver
{
    /// This pattern matches the four values for the two ranges in each line of the puzzle input.
    private static final Pattern RANGE_PATTERN =
            Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");
    
    /// These records store the parsed data for a single line of the puzzle input.
    record Range(int startingSection, int endingSection) {}
    record RangePair(Range firstRange, Range secondRange) {}
    
    /// Parses a line of the puzzle input to create a pair of ranges.
    ///
    /// The puzzle input contains two ranges on each line, such as `2-4,4-6`.
    ///
    /// Here the first range would be `2-4` and the second range would be `4-6`.
    ///
    /// @param line a line of the puzzle input.
    /// @return the pair of ranges.
    private static RangePair parseRanges(String line)
    {
        Matcher matcher = RANGE_PATTERN.matcher(line);
        
        if (matcher.find())
        {
            Range firstRange = new Range(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2))
            );
            
            Range secondRange = new Range(
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4))
            );
            
            return new RangePair(firstRange, secondRange);
        }
        else
        {
            throw new IllegalArgumentException(
                    "Error: Unexpected input format encountered with line value: " + line
            );
        }
    }
    
    /// Calculates the number of instances where one of the two ranges fully contains another.
    ///
    /// Time Complexity: O(n)
    /// - A single pass is done over the puzzle input.
    ///
    /// Space Complexity: O(1)
    /// - One pair of ranges is stored at a time for each line of the puzzle input.
    ///
    /// @param puzzleInput the puzzle input.
    /// @return the number of instances where one of the two ranges fully contains another.
    @Override
    public Object partOne(List<String> puzzleInput)
    {
        int fullyContainedRanges = 0;
        
        for (String line : puzzleInput)
        {
            RangePair ranges = parseRanges(line);
            
            if (isFullyContainedRange(ranges))
            {
                fullyContainedRanges++;
            }
        }
        
        return fullyContainedRanges;
    }
    
    /// Returns true if one of the two ranges are fully contained by the other, and false
    /// otherwise.
    ///
    /// @param ranges a list of ranges.
    /// @return true if one of the two ranges are fully contained by the other, and false
    ///         otherwise.
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
    /// Time Complexity: O(n)
    /// - A single pass is done over the puzzle input.
    ///
    /// Space Complexity: O(1)
    /// - One pair of ranges is stored at a time for each line of the puzzle input.
    ///
    /// @param puzzleInput the puzzle input.
    /// @return the number of instances where one of the two ranges fully contains another.
    @Override
    public Object partTwo(List<String> puzzleInput)
    {
        int overlappingRanges = 0;
        
        for (String line : puzzleInput)
        {
            RangePair ranges = parseRanges(line);
            
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
