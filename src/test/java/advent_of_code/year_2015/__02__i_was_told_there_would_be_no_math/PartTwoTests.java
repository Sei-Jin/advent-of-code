package advent_of_code.year_2015.__02__i_was_told_there_would_be_no_math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PartTwoTests
{
    @Test
    void increasingDimensions()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("2x3x4");
        Assertions.assertEquals(34, new Solution().partTwo(inputLines));
    }
    
    @Test
    void smallSmallestSide()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("1x1x10");
        Assertions.assertEquals(14, new Solution().partTwo(inputLines));
    }
    
    @Test
    void evenDimensions()
    {
        List<String> inputLines = new ArrayList<>();
        inputLines.add("5x5x5");
        Assertions.assertEquals(145, new Solution().partTwo(inputLines));
    }
}
