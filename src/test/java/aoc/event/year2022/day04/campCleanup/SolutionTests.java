package aoc.event.year2022.day04.campCleanup;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void example()
    {
        List<String> input = new ArrayList<>(
                Arrays.stream("""
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8
                """.split("\n")).toList()
        );
        assertEquals(2, new Solution().partOne(input));
        assertEquals(4, new Solution().partTwo(input));
    }
    
    @Test
    void checkOverlapping()
    {
        List<String> input = new ArrayList<>(
                Arrays.stream("""
                5-5,4-6
                4-6,5-5
                """.split("\n")).toList()
        );
        assertEquals(2, new Solution().partTwo(input));
    }
}
